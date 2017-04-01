package brownshome.scriptwars.server.game;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import brownshome.scriptwars.server.connection.ConnectionHandler;
import brownshome.scriptwars.server.game.tanks.TankGame;

public class GameType {
	static interface GameCreator {
		Game get() throws GameCreationException;
	}
	
	static Map<String, GameType> publicGames = new HashMap<>();
	static Map<String, GameType> debugGames = new HashMap<>();
	
	public static void addType(Class<TankGame> clazz) throws GameCreationException {
		GameType type = new GameType(clazz);
		publicGames.put(type.getName(), type);
	}
	
	public static Collection<GameType> getGameTypes() {
		return publicGames.values();
	}
	
	public static void addDebugType(Class<TestGame> clazz) throws GameCreationException {
		GameType type = new GameType(clazz);
		debugGames.put(type.getName(), type);
	}
	
	public static GameType getGameType(String string) {
		return publicGames.get(string);
	}
	
	GameCreator constructor;
	String name;
	String description;
	
	ReentrantReadWriteLock gamesLock = new ReentrantReadWriteLock();
	Collection<Game> games = new ArrayList<>();
	Set<Runnable> onListUpdate = new HashSet<>();
	
	public GameType(Class<? extends Game> clazz) throws GameCreationException {
		Constructor<? extends Game> constructor;
		
		try {
			constructor = clazz.getConstructor(GameType.class);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new GameCreationException("Game " + clazz.getSimpleName() + " did not have a suitable constructor.", e);
		}
		
		this.constructor = () -> {
			try {
				Game.getActiveGamesLock().writeLock().lock();
				Game game = constructor.newInstance(this);
				game.addToSlot();
				game.start();
				return game;
			} catch (OutOfIDsException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new GameCreationException("Unable to instantiate game", e);
			} finally {
				Game.getActiveGamesLock().writeLock().unlock(); //This will always be executed, even if the function returns normally
			}
		};
		
		try {
			name = (String) clazz.getMethod("getName").invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new GameCreationException("Game " + clazz.getSimpleName() + " did not define \'static String getName()\'.", e);
		}
		
		try {
			description = (String) clazz.getMethod("getDescription").invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new GameCreationException("Game " + clazz.getSimpleName() + " did not define \'static String getDescription()\'.", e);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getPlayerCount() {
		gamesLock.readLock().lock();
		try {
			return games.stream().map(Game::getConnectionHandler).mapToInt(ConnectionHandler::getPlayerCount).sum();
		} finally {
			gamesLock.readLock().unlock();
		}
	}
	
	public String getDescription() {
		return description;
	}
	
	/** generates a new ID 
	 * @throws GameCreationException if a new game could not be created and the existing one is full */
	public int getUserID() throws GameCreationException {
		return getAvailableGame().getConnectionHandler().getID();
	}
	
	public Game getAvailableGame() throws GameCreationException {
		gamesLock.writeLock().lock();
		try {
			for(Game game : games) {
				if(game.hasSpaceForPlayer())
					return game;
			}

			Game availableGame = constructor.get();
			games.add(availableGame);
			signalListUpdate();
			return availableGame;
		} finally {
			gamesLock.writeLock().unlock();
		}
	}
	
	public Collection<Game> getGames() {
		gamesLock.readLock().lock();
		try {
			return new ArrayList<>(games); //return a copy for thread safety
		} finally {
			gamesLock.readLock().unlock();
		}
	}

	public synchronized void onListUpdate(Runnable update) {
		onListUpdate.add(update);
	}

	public synchronized void removeOnListUpdate(Runnable update) {
		onListUpdate.remove(update);
	}

	public synchronized void signalListUpdate() {
		onListUpdate.forEach(Runnable::run);
	}
}

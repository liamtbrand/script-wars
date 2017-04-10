package brownshome.scriptwars.server.game.snake;

import java.nio.ByteBuffer;

import brownshome.scriptwars.server.game.DisplayHandler;
import brownshome.scriptwars.server.game.Game;
import brownshome.scriptwars.server.game.GameType;
import brownshome.scriptwars.server.game.Player;

public class SnakeGame extends Game {
	
	protected SnakeGame(GameType type) {
		super(type);
	}

	public static String getName() {
		return "Snakes";
	}

	public static String getDescription() {
		return "A snake game. Kill and eat your opponents!";
	}

	@Override
	public boolean hasPerPlayerData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaximumPlayers() {
		// TODO Auto-generated method stub
		return 16;
	}

	@Override
	public int getTickRate() {
		// TODO Auto-generated method stub
		return 200;
	}

	@Override
	protected void tick() {
		// Gets called every tick of the game.
	}

	@Override
	public int getDataSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getData(Player player, ByteBuffer data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processData(ByteBuffer data, Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void displayGame(DisplayHandler handler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPreferedConnectionType() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

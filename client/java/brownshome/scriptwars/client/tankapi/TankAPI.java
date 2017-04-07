package brownshome.scriptwars.client.tankapi;

import java.util.ArrayList;
import java.util.List;

import brownshome.scriptwars.client.Network;

/**
 * This is a wrapper over the Network class that gives an api to control the Tank.
 * This can be used for controlling the tank without knowing the
 * underlying network implementation.<br />
 * <br />
 * NOTE: This code is currently in beta.
 *
 * @author Liam T. Brand
 * @author James Brown
 *
 */
public class TankAPI {

	public enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	/**
	 * This class stores a cartesian coordinate
	 *
	 */
	public class Coordinate {
		private int _x;
		private int _y;

		public Coordinate(int x, int y){
			_x = x;
			_y = y;
		}

		public int getX(){
			return _x;
		}
		public int getY(){
			return _y;
		}
	}

	public class Tank {
		private Coordinate _coord;

		public Tank(Coordinate c){
			_coord = c;
		}

		public Coordinate getCoordinates(){
			return _coord;
		}
	}

	public class Shot {
		private Coordinate _coord;
		private Direction _direction;

		public Shot(Coordinate c, Direction d){
			_coord = c;
			_direction = d;
		}

		public Coordinate getCoordinates(){
			return _coord;
		}
		public Direction getDirection(){
			return _direction;
		}
	}
	
	public class GameMap {
		
		public static final boolean SPACE = false;
		public static final boolean WALL = true;
		
		private final boolean[][] _walls;
		
		protected GameMap(final boolean[][] walls){
			_walls = walls;
		}
		
		public boolean isWall(Coordinate c){
			return _walls[c.getY()][c.getX()] == true;
		}
		
		public boolean[][] getRawData(){
			return _walls;
		}
	}

	public enum Action {
		NOACTION, MOVE, SHOOT;
	}

	private boolean _isAlive;
	
	private Tank _me;
	
	private GameMap _map;

	private List<Tank> _tanks;
	private List<Shot> _shots;

	private boolean _send = false;

	private int _actionByte;
	private int _directionByte;

	public TankAPI(int id, String address, int port, String username){
		Network.connect(id, address, port, username);
	}

	/**
	 * This is called at the end of each tick.
	 * This will cause data for the last tick to be sent to the server.
	 */
	private void setSendData(){
		if(_send){

			Network.sendByte(_actionByte);                    //Action Byte, in this case MOVE
			Network.sendByte(_directionByte);                    //Direction Byte, in this case DOWN

		}else{
			_send = true;
		}

		_actionByte = Action.NOACTION.ordinal(); // Default action
		_directionByte = Direction.UP.ordinal(); // Default direction //TODO fix
	}

	/**
	 * This is the main api loop.
	 * This should be called from a while loop inside which is
	 * the main code of the AI. See the example AI.
	 * @return
	 */
	public boolean nextTick(){

		setSendData();

		while(!Network.nextTick()){
			// Wait for next tick...
		}

		_isAlive = Network.getByte() == 1;          // Is the player alive
		if(_isAlive) {
			int x = Network.getByte();              // X position
			int y = Network.getByte();              // Y position
			
			_me = new Tank(new Coordinate(x,y));
			
			int width = Network.getByte();          // game width
			int height = Network.getByte();         // game height

			boolean[][] walls = new boolean[height][width];

			for(int row = 0; row < walls.length; row++) {
				for(int column = 0; column < walls[row].length; column++) {
					if(Network.getBoolean()) {      //Is wall
						walls[row][column] = GameMap.WALL;
					} else {
						walls[row][column] = GameMap.SPACE;
					}
				}
			}
			
			_map = new GameMap(walls);

			int tanks = Network.getByte();          //Number of tanks
			_tanks = new ArrayList<Tank>();
			for(int i = 0; i < tanks; i++) {
				int tankX = Network.getByte();      //Tank x
				int tankY = Network.getByte();      //Tank y
				_tanks.add(new Tank(new Coordinate(tankX,tankY)));
			}

			int shots = Network.getByte();          //Number of Shots
			_shots = new ArrayList<Shot>();
			for(int i = 0; i < shots; i++) {
				int shotX = Network.getByte();      //Shot x
				int shotY = Network.getByte();      //Shot y
				//Shot direction
				_shots.add(new Shot(new Coordinate(shotX,shotY),Direction.values()[Network.getByte()]));
			}

		} else {
			Network.sendByte(0);                    //Send stuff always, or get dropped
			System.out.println("We Are Dead");
		}

		return true;
	}

	/**
	 * Move the tank in a direction.
	 * @param direction
	 */
	public void move(Direction direction) {
		_actionByte = Action.MOVE.ordinal();
		_directionByte = direction.ordinal();
	}
	
	/**
	 * Shoots a bullet in the specified direction.
	 * @param direction
	 */
	public void shoot(Direction direction) {
		_actionByte = Action.SHOOT.ordinal();
		_directionByte = direction.ordinal();
	}
	
	/**
	 * Returns a list of tank objects which are currently visible.
	 * At the time of writing this is any tank in line of sight.
	 * @return
	 */
	public List<Tank> getVisibleTanks(){
		return _tanks;
	}
	
	/**
	 * Returns the tank object for the current player.
	 * @return
	 */
	public Tank me(){
		return _me;
	}
	
	/**
	 * Returns a GameMap object.
	 * Raw data can be extracted from here for path finding etc.
	 * @return
	 */
	public GameMap getMap(){
		return _map;
	}
	
}

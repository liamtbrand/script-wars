package brownshome.scriptwars.client.snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import brownshome.scriptwars.client.Network;
import brownshome.scriptwars.client.snake.api.Food;
import brownshome.scriptwars.client.snake.api.Snake;

/**
 * This is the API for the snake game.
 * <br>
 * Note: This is currently under development.
 * 
 * @author Liam T. Brand
 *
 */

public class SnakeAPI {
	
	// Food on the map. Maps food_id to Food.
	private Map<Integer,Food> _food;
	
	// Snakes visible.
	private Map<Integer,Snake> _snakes;
	
	// Myself
	private int _myID;

	private boolean _firstSend = false;
	
	public SnakeAPI(int id, String address, int port, String username) {
		Network.connect(id, address, port, username);
		initializeConnection();
		_firstSend = true;
	}
	
	private void initializeConnection(){
		_food = new HashMap<Integer,Food>();
		_snakes = new HashMap<Integer,Snake>();
	}

	/**
	 * This is called at the end of each tick.
	 * This will cause data for the last tick to be sent to the server.
	 */
	private void setSendData(){
		if(!_firstSend){

			//Network.sendByte(_actionByte);                    //Action Byte, in this case MOVE
			//Network.sendByte(_directionByte);                    //Direction Byte, in this case DOWN

		}else{
			_firstSend = false;
		}

		//_actionByte = Action.NOACTION.ordinal(); // Default action
		//_directionByte = Direction.UP.ordinal(); // Default direction //TODO fix
	}

	/**
	 * This is the main API loop.
	 * This should be called from a while loop inside which is
	 * the main code of the AI. See the example AI.
	 * @return True when we have entered into the next game tick.
	 */
	public boolean nextTick(){

		// Set the data to be sent.
		setSendData();

		while(!Network.nextTick()){
			// Wait for next tick...
		}

		// Receive all data.

		return true;
	}
	
}

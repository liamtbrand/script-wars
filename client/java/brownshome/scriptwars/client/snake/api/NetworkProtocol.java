package brownshome.scriptwars.client.snake.api;

public class NetworkProtocol {
	
	public enum Server{
		REQUESTSNAKE,
		/*
		 * Requests a Client.SNAKE response.
		 * All nearby snakes will be sent.
		 */
		
		REQUESTFOOD,
		/*
		 * Requests all food to be sent as 
		 */
		
		PING;
	}
	
	public enum Client{
	
		/*
		 * Network packet formatting:
		 * DATATYPE,DATA
		 * 
		 */
		
		PING,
		/*
		 * Ping should be sent on a tick when nothing
		 * is happening but we want to stay connected.
		 */
		
		SNAKE,
		/*
		 * This indicates incoming data for an entire snake.
		 * DATA Formatting:
		 * (int) ID,(String) NAME,(int) SEGMENTS
		 * 
		 * for SEGMENTS following SNAKE packets:
		 * (int) X, (int) Y
		 */
		
		FOOD,
		/*
		 * This indicates incoming data for food.
		 * DATA Formatting:
		 * (int) ID,(double) X,(double) Y,(FoodValue) FOODVALUE.
		 */
		
		SNAKEFRAGMENT,
		
		
		
		TICK;
	}
}

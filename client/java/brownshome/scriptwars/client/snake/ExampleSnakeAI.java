package brownshome.scriptwars.client.snake;

public class ExampleSnakeAI {

	public static void main(String[] args) {
		
		int id = 65760; // Default id if none is passed (for testing)
		if(args.length > 0){
			id = Integer.valueOf(args[0]);
		}

		SnakeAPI api = new SnakeAPI(id, "13.55.154.170", 35565, "John Smith");

		while(api.nextTick() == SnakeAPI.ConnectionStatus.CONNECTED) {

			
			
		}
	}
	
}

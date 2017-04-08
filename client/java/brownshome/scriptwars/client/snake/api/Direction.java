package brownshome.scriptwars.client.snake.api;

public class Direction {
	
	private Coordinates _c;

	/**
	 * Direction will be stored as a unit vector.
	 * @param c
	 */
	public Direction(double x, double y){
		toUnitVector(x,y);
	}
	
	public Direction(Coordinates c){
		toUnitVector(c.getX(),c.getY());
	}
	
	private void toUnitVector(double x,double y){
		double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		if(length != 0){
			_c = new Coordinates(x/length,y/length);
		}else{
			// Direction doesn't exist for the trivial solution.
			_c = new Coordinates(0,0);
			System.err.println("Caught division by 0... Direction doesn't exist?");
		}
	}
	
}

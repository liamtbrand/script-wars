package brownshome.scriptwars.client.snake.api;

public class Coordinates {
	private double _x;
	private double _y;

	public Coordinates(double x, double y){
		_x = x;
		_y = y;
	}

	public double getX(){
		return _x;
	}
	public double getY(){
		return _y;
	}
}
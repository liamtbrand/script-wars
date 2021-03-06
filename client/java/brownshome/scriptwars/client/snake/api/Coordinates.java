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
	
	public Coordinates newCoordinatesFromVector(Vector v){
		return new Coordinates(_x+v.getX(),_y+v.getY());
	}
	
	public void applyVector(Vector v){
		_x += v.getX();
		_y += v.getY();
	}
}
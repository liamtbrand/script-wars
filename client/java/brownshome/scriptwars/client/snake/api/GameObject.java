package brownshome.scriptwars.client.snake.api;

public class GameObject {

	private Coordinates _coords;
	private double _collisionRadius;
	
	public GameObject(Coordinates coords, double collisionRadius){
		_coords = coords;
		_collisionRadius = collisionRadius;
	}
	
	public Coordinates getCoordinates(){
		return _coords;
	}
	
	public double getCollisionRadius(){
		return _collisionRadius;
	}
	
	public boolean collisionWith(GameObject go){
		
		// If distance between is less than the sum of their colision radii, they have collided.
		double distance = _collisionRadius + go._collisionRadius;
		
		// First try x distance
		double xDistance = Math.abs(_coords.getX()-go._coords.getX());
		if(xDistance > distance){
			return false;
		}
		
		// Then try y distance
		double yDistance = Math.abs(_coords.getY()-go._coords.getY());
		if(yDistance > distance){
			return false;
		}
		
		// We must be close... Check for a collision.
		return Math.sqrt(Math.pow(xDistance,2) + Math.pow(yDistance,2)) < distance;
	}
	
}
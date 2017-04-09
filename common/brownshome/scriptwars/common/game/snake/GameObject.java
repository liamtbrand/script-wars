package brownshome.scriptwars.common.game.snake;

public class GameObject {

	private Coordinates _coords;
	private Radius _collisionRadius;
	
	public GameObject(Coordinates coords, Radius collisionRadius){
		_coords = coords;
		_collisionRadius = collisionRadius;
	}
	
	public Coordinates getCoordinates(){
		return _coords;
	}
	
	public Radius getCollisionRadius(){
		return _collisionRadius;
	}
	
	/**
	 * Returns true if there is a collision between the two objects.
	 * @param go
	 * @return
	 */
	public boolean collisionWith(GameObject go){
		
		// If distance between is less than the sum of their colision radii, they have collided.
		double distance = _collisionRadius.getRadius() + go._collisionRadius.getRadius();
		
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
	
	public Vector getVectorTo(GameObject go){
		return new Vector(go._coords.getX()-_coords.getX(),go._coords.getY()-_coords.getY());
	}
	
	public Vector getVectorFrom(GameObject go){
		return new Vector(_coords.getX()-go._coords.getX(),_coords.getY()-go._coords.getY());
	}
	
}

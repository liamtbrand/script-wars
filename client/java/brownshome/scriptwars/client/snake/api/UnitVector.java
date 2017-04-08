package brownshome.scriptwars.client.snake.api;

public class UnitVector extends Vector{

	/**
	 * Direction will be stored as a unit vector.
	 * @param c
	 */
	public UnitVector(Vector v){
		super((v.getLength() > 0 ? v.getX()/v.getLength() : 0),(v.getLength() > 0 ? v.getY()/v.getLength() : 0));
	}
	
}

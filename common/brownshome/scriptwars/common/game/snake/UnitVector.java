package brownshome.scriptwars.common.game.snake;

public class UnitVector extends Vector{

	/**
	 * Direction will be stored as a unit vector.
	 * @param v
	 */
	public UnitVector(Vector v){
		super((v.getLength() > 0 ? v.getX()/v.getLength() : 0),(v.getLength() > 0 ? v.getY()/v.getLength() : 0));
	}
	
}

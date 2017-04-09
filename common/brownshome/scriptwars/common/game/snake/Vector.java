package brownshome.scriptwars.common.game.snake;

public class Vector extends Coordinates {
	
	private double _length;

	public Vector(double x, double y){
		super(x,y);
		_length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public Vector scaleBy(double coefficient){
		_length = coefficient * _length;
		return this;
	}
	
	public double getLength(){
		return _length;
	}
	
	public UnitVector toUnitVector(){
		return new UnitVector(this);
	}
	
	public Vector sumWith(Vector v){
		return new Vector(v.getX()+this.getX(),v.getY()+this.getY());
	}
}

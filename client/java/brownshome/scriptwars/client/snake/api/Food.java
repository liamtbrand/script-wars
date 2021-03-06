package brownshome.scriptwars.client.snake.api;

public class Food extends GameObject{
	
	public enum FoodValue {
		LOW, MEDIUM, HIGH;
	}

	private FoodValue _value;
	
	public Food(Coordinates coords, FoodValue value){
		super(coords,new Radius(1));
		_value = value;
	}
	
	public FoodValue getValue(){
		return _value;
	}
	
}

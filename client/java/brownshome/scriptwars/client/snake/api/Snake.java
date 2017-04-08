package brownshome.scriptwars.client.snake.api;

import java.util.List;

public class Snake {
	
	public enum MoveSpeed{
		SLOW, FAST;
	}
	
	private String _displayName = "";
	
	public class UpdateFragment{
		private Direction _direction;
		private MoveSpeed _speed;
		private int _growth;
		
		public UpdateFragment(Direction direction, MoveSpeed speed, int growth){
			_speed = speed; // Speed we are moving at.
			_direction = direction; // Direction to move head.
			_growth = growth; // Amount to grow by.
		}
		
		public int getGrowth(){
			return _growth;
		}
	}
	
	public class Segment extends GameObject{
		
		public Segment(Coordinates c, double size){
			super(c,size);
		}
		
	}
	
	private List<Segment> _segments;
	
	public Snake(List<Segment> segments){
		_segments = segments;
	}
	
	/**
	 * Moves the snake along the new updated coordinates.
	 * Each segment should move in the direction of the one before it,
	 * but it should not move the whole way. This slight reduction in distance
	 * will cause the snake to be able to constrict when circling etc.
	 * @param updateCoords
	 */
	public void applyUpdateFragments(List<UpdateFragment> updateFragments){
		for(UpdateFragment updateFragment : updateFragments){
			
			// Grow
			for(int i = 0; i < updateFragment.getGrowth(); ++i){
				// Get direction of tail.
				
			}
			
			// Move
			
			Coordinates lastCoords;
			for(Segment segment : _segments){
				lastCoords = segment.getCoordinates();
				//segment = new Segment(updateCoords);
			}
		}
	}
	
	public void setDisplayName(String name){
		_displayName = name;
	}
	
}

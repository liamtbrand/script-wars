package brownshome.scriptwars.client.snake.api;

import java.util.List;

public class Snake {
	
	public enum MoveSpeed{
		SLOW, FAST;
	}
	
	private String _displayName = "";
	
	public class UpdateFragment{
		private UnitVector _direction;
		private MoveSpeed _speed;
		private int _growth;
		
		public UpdateFragment(UnitVector direction, MoveSpeed speed, int growth){
			_speed = speed; // Speed we are moving at.
			_direction = direction; // Direction to move head.
			_growth = growth; // Amount to grow by.
		}
	}
	
	public class Segment extends GameObject{
		
		public Segment(Coordinates c, Radius r){
			super(c,r);
		}
		
	}
	
	// Tail at 0, Head at _segments.size()-1.
	private List<Segment> _segments;
	private Radius _segmentRadius;
	private static final double _movement = 0.95;
	
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
			for(int i = 0; i < updateFragment._growth; ++i){
				// Get direction of tail.
				Vector v = _segments.get(1).getVectorTo(_segments.get(0));
				v.toUnitVector().scaleBy(0.5); // Make vector of length 0.5.
				// Add the new segment.
				Segment last = _segments.get(0);
				_segments.add(0,new Segment(last.getCoordinates().applyVector(v),_segmentRadius));
			}
			
			// Move
			UnitVector direction = updateFragment._direction;
			if(updateFragment._speed == MoveSpeed.SLOW){
				Vector v = _segments.get(_segments.size()-1).getVectorTo(_segments.get(_segments.size()-2));
				v.scaleBy(_movement);
			}
			
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

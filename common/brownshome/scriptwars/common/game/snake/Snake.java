package brownshome.scriptwars.common.game.snake;

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
	
	private void move(UnitVector direction){
		
		// Move all the segments in the snake.
		
		Vector v;
		int segmentCount = _segments.size();
		int i = 0;
		Segment lastSegment = null;
		for(Segment segment : _segments){
			
			if(i > 0){ // If we aren't the very last segment.
				
				v = lastSegment.getVectorTo(segment);
				v = v.scaleBy(_movement); // Scale back the movement slightly.
				lastSegment.getCoordinates().applyVector(v);
				
				// If we have reached the head.
				if(i == segmentCount-1){
					
					// Move the head in the direction it is going.
					double movespeed = (_segmentRadius.getRadius()*2)*_movement;
					segment.getCoordinates().applyVector(direction.scaleBy(movespeed));
					
				}
				
			}else{
				// This is the last tail segment.
				// We ignore it because we need to lag behind one in our iteration.
			}
			
			lastSegment = segment; // Record the last segment.
			++i;
			
		}
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
			
			Vector v;
			
			// Grow
			for(int i = 0; i < updateFragment._growth; ++i){
				// Get direction of tail.
				v = _segments.get(1).getVectorTo(_segments.get(0));
				v.toUnitVector().scaleBy(0.5); // Make vector of length 0.5.
				// Add the new segment.
				Segment last = _segments.get(0);
				_segments.add(0,new Segment(last.getCoordinates().newCoordinatesFromVector(v),_segmentRadius));
			}
			
			// Compute the current radius
			_segmentRadius.setRadius(0.5 + (_segments.size()*0.01));
			
			// Move
			UnitVector direction = updateFragment._direction;
			this.move(direction);
			if(updateFragment._speed == MoveSpeed.FAST){ // If we are boosting, we move twice the speed.
				this.move(direction);
			}
			
		}
	}
	
	public void setDisplayName(String name){
		_displayName = name;
	}
	
	public String getDisplayName(){
		return _displayName;
	}
	
}

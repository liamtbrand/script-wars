package brownshome.scriptwars.client.snake.api;

import java.util.ArrayList;
import java.util.List;

public class Snake {
	
	private String _displayName = "";
	
	public class UpdateFragment{
		private Coordinates _updatedCoords;
		private int _growth;
		
		public UpdateFragment(Coordinates updatedCoords, int growth){
			_updatedCoords = updatedCoords; // New position of head
			_growth = growth; // Amount to grow by
		}
		
		public Coordinates getUpdatedCoords(){
			return _updatedCoords;
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
	 * @param updateCoords
	 */
	public void applyUpdateFragments(List<UpdateFragment> updateFragments){
		for(UpdateFragment updateFragment : updateFragments){
			
			// Grow
			for(int i = 0; i < updateFragment.getGrowth(); ++i){
				
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

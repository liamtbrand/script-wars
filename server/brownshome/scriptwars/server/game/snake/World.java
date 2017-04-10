package brownshome.scriptwars.server.game.snake;

public class World {

	// Spawn Food
	
	// Spawn Snake
	
	// Apply Update Fragments.
	
	/*
	 * Update Fragments.
	 * 
	 * For update fragments we will need to track who had recieved what update fragments.
	 * Ticks should be counted and each fragment should be assigned a tick.
	 * 
	 * UnitVector -> 2 * double : 8 + 8 bytes.
	 * MoveSpeed -> Ordinal (boolean) : 1 bit.
	 * _growth -> unsigned 8 bit integer.
	 * 
	 * Total size: 16*8+1+8 = 128+1+8 = 137 bits.
	 * + Packet type of (ordinal -> unsigned 8 bit integer) NetworkProtocol. 1 byte.
	 * Total packet size: 8+137 = 145 bits.
	 * 
	 */
	
}

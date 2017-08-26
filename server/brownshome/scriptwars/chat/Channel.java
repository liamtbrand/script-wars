package brownshome.scriptwars.chat;

import java.util.HashSet;
import java.util.Set;

public class Channel {
	
	private Set<ChatUser> users;
	
	public Channel() {
		users = new HashSet<ChatUser>();
	}
	
	protected void sendMessage(String message) {
		for(ChatUser user : users) {
			user.sendMessage(message);
		}
	}

}

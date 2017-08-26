package brownshome.scriptwars.chat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Chat {
	
	private Chat chat;
	
	private Map<String,Channel> channels;
	private Map<String,ChatUser> users;

	private Chat() {
		channels = new HashMap<String,Channel>();
	}
	
	public Chat getChat() {
		if(chat == null) {
			chat = new Chat();
		}
		return chat;
	}
	
	public Set<String> getChannels() {
		return Collections.unmodifiableSet(channels.keySet());
	}
	
	public Channel getChannel(String name) {
		if(channels.containsKey(name)) {
			return channels.get(name);
		} else {
			assert false;
			return null;
		}
	}
	
	public boolean userExists(String name) {
		return users.containsKey(name);
	}
	
	public ChatUser getUser(String name) {
		if(users.containsKey(name)) {
			return users.get(name);
		} else {
			assert false;
			return null;
		}
	}
	
}

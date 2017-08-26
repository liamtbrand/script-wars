package brownshome.scriptwars.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatUser {
	
	private volatile List<String> messages;
	
	public ChatUser() {
		this.messages = new ArrayList<String>();
	}
	
	/**
	 * Send a message to this chat user.
	 * @param message
	 */
	protected void sendMessage(String message) {
		this.messages.add(message);
	}
	
	protected List<String> getMessages() {
		List<String> messages = this.messages;
		this.messages = new ArrayList<String>();
		return messages;
	}
	
}

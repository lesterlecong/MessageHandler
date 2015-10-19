package messagehandler;

import java.util.List;

public interface MessageHandler {
	public void handleMessage(String message);
	public void addMessage(String message);
	public List<String> messages();
	public void clearMessages();
}

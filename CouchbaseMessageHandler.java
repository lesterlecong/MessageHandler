package messagehandler;

import java.util.ArrayList;
import java.util.List;

public class CouchbaseMessageHandler implements MessageHandler {
	
	public CouchbaseMessageHandler(){
		
	}
	
	public void handleMessage(String message){
		System.out.println("Handle message: " + message);
	}
	
	public void addMessage(String message){
		
	}
	public List<String> messages(){
		return new ArrayList<String>();
	}
	
	public void clearMessages(){
		
	}
}

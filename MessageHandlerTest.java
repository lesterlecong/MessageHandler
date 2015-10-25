package com.lesterprojects.messagehandler;

public class MessageHandlerTest {

	public static void main(String[] args) {
			
		
		
		CouchbaseMessageHandler cbMessageHandler = new CouchbaseMessageHandler();
		ClientListener clientListener = new ClientListener(cbMessageHandler, "localhost", 8089);
		clientListener.start();
			
	}

}

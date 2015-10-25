package com.lesterprojects.messagehandler;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CouchbaseMessageHandler implements MessageHandler {
	
	private List<String> m_messages;
	
	public CouchbaseMessageHandler(){
		m_messages = new ArrayList<String>();
		addMessage("Hello World this lester from earth can you reply on my message through skype, mail or facebook?");
		addMessage("Hello World this lester from earth receive this message or else.....nothing :D");//sample
	
	}
	
	public void handleMessage(String message){
		System.out.println("Handle message: " + message);
		
	}
	
	public void addMessage(String message){
		
		NumberFormat formatter = new DecimalFormat("000");
		String messageSize = formatter.format(message.length());
		message = messageSize + message;
		System.out.println(message);
		m_messages.add(message);
	}
	public List<String> messages(){
		return m_messages;
	}
	
	public void clearMessages(){
		m_messages.clear();
	}
}

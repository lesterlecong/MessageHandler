package com.lesterprojects.messagehandler;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.lesterprojects.messageparser.DatabaseMessageParser;
import com.lesterprojects.messageparser.MessageParser;

public class CouchbaseMessageHandler implements MessageHandler {
	
	private List<String> m_messages;
	private DatabaseMessageParser m_parser;
	
	public CouchbaseMessageHandler(){
		m_messages = new ArrayList<String>();
	}
	
	public void handleMessage(String message){
		System.out.println("Handle message: " + message);
		if(m_parser != null){
			m_parser.parse(message);
			System.out.println("Database Info: " + m_parser.databaseInfo());
			System.out.println("Data:" + m_parser.data().toString());
		}
	}
	
	public void addMessageParser(MessageParser messageParser){
		m_parser = (DatabaseMessageParser) messageParser;
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

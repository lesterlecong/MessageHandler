package com.lesterprojects.messagehandler;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lesterprojects.messageparser.DatabaseInfoKey;
import com.lesterprojects.messageparser.DatabaseMessageParser;
import com.lesterprojects.messageparser.MessageParser;
import com.lesterprojects.cbloperationhandler.CBLOperationHandler;
import com.lesterprojects.cbloperationhandler.CBLOperationHandlerFactory;

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
			
			Map<String, Object> result = new HashMap<String, Object>();
			
			
			CBLOperationHandler handler = CBLOperationHandlerFactory.instance()
										 .operationHandler(m_parser.databaseInfo()
									     .get(DatabaseInfoKey.COMMAND).toString());
				
			if(handler != null){
				result = handler.operate(m_parser.databaseInfo(), m_parser.data());
				System.out.println("Result: " + result.toString());
			}
			
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

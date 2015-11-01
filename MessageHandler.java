package com.lesterprojects.messagehandler;

import java.util.List;

import com.lesterprojects.messageparser.MessageParser;

public interface MessageHandler {
	public void handleMessage(String message);
	public void addMessageParser(MessageParser messageParser);
	public void addMessage(String message);
	public List<String> messages();
	public void clearMessages();
}

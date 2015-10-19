package messagehandler;

import java.io.*;
import java.net.*;
import java.util.List;

public class MessageServer implements Runnable {
	
	private String m_host;
	private MessageHandler m_messageHandler;
	private Socket m_socket;
	private Thread m_thread;
	private int clientUnresponsiveCount;
	private BufferedReader m_bufferedReader;
	private PrintWriter m_printWriter;
	
	private boolean m_stopServer = false;
	
	private static final int timeout_length = 30000;

	
	public MessageServer(String host, Socket socket, MessageHandler messageHandler) { 
		m_messageHandler = messageHandler;
		m_socket = socket;
		m_host = host;
		clientUnresponsiveCount = 0;
		try {	
			m_socket.setSoTimeout (timeout_length);
		}
		catch (SocketException se) {
			System.err.println ("Unable to set socket option SO_TIMEOUT");
		}
		
		try {
			m_socket.setKeepAlive(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run(){

		setupSocketIO();
		
	
		while(true){
		   sendMessage();
		   receiveMessage();
		   
		   if(m_stopServer){
			   break;
		   }
		}
		
		System.out.println("Closing Server....");
		ClientCounter.clientCounter = ClientCounter.clientCounter -1 ;
		
	}
	
	public void start(){
		if(m_thread == null){
			m_thread = new Thread(this, m_host);
			m_thread.start();
		}
	}
	
	private void setupSocketIO(){
		try {
			m_bufferedReader = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
			m_printWriter = new PrintWriter(m_socket.getOutputStream(), true);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	private void receiveMessage(){
		if(m_messageHandler == null){
			return;
		}
		
		try {
			if(m_bufferedReader.ready()){
				String readLine = m_bufferedReader.readLine().trim();
				
				if(!readLine.isEmpty()){
					m_messageHandler.handleMessage(readLine);
				}else{
					stopServer();
				}
			}else{
				if(clientUnresponsiveCount >= 100){
					stopServer();
					clientUnresponsiveCount = 0;
				}
				clientUnresponsiveCount++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(){
		if(m_messageHandler == null){
			return;
		}
			
		if(!m_messageHandler.messages().isEmpty()){
				
			List<String> messages = m_messageHandler.messages();
				
			for(String message : messages){
				m_printWriter.print(message);
			}

			m_messageHandler.clearMessages();
		}
		
	}


	
	private void stopServer(){
		try {
			m_socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		m_stopServer = true;
		System.out.println("I am out! Create new connection");
	}
	
}

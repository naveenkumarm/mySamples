/**
	WebSocket.java
	Author @ Suresh Govindaraj
	Epro Technologies
 **/

package com.epro.ws;

import java.io.IOException;

import org.eclipse.jetty.websocket.WebSocket;
 

public class ChatWebSocket implements  WebSocket.OnTextMessage {
	 
    public Connection connection;

    public void onOpen(Connection connection) {
    	System.out.println("open....");
        // Client (Browser) WebSockets has opened a connection.
        // 1) Store the opened connection
        this.connection = connection;
        // 2) Add ChatWebSocket in the global list of ChatWebSocket
        // instances
        // instance.
        ChatWebSocketHandler.webSockets.add(this);
    }

    public void onMessage(String data) {
    	System.out.println(data);
        // Loop for each instance of ChatWebSocket to send message server to
        // each client WebSockets.
        try {
            for (ChatWebSocket webSocket : ChatWebSocketHandler.webSockets) {
                // send a message to the current client WebSocket.
                webSocket.connection.sendMessage(data);
            }
        } catch (IOException x) {
            // Error was detected, close the ChatWebSocket client side
            this.connection.disconnect();
        }

    }

    public void onClose(int closeCode, String message) {
        // Remove ChatWebSocket in the global list of ChatWebSocket
        // instance.
    	ChatWebSocketHandler.webSockets.remove(this);
    	System.out.println("Client Removed....");
    }
}



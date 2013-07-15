/**
	ChatWebSocketHandler.java
	Author @ Suresh Govindaraj
	Epro Technologies
 **/

package com.epro.ws;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketHandler;

public class ChatWebSocketHandler extends WebSocketHandler {
	 
	public static Set<ChatWebSocket> webSockets = new CopyOnWriteArraySet<ChatWebSocket>();
	 
    public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
    	System.out.println("connect...."+request.getRemoteAddr());
        return new ChatWebSocket();
    }
    
}


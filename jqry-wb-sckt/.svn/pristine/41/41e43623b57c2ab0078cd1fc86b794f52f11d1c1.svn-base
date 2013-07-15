/**
	JettyMain.java
	Author @ Suresh Govindaraj
	Epro Technologies
 **/

package com.epro.ws;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;

public class JettyMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Server server = new Server(7070);

	       /* ContextHandler context = new ContextHandler();
	        context.setContextPath("/ws");
	        context.setResourceBase(".");
	        context.setClassLoader(Thread.currentThread().getContextClassLoader());
	        server.setHandler(context);*/
	  
			
			ChatWebSocketHandler chatWebSocketHandler = new ChatWebSocketHandler();
			chatWebSocketHandler.setHandler(new DefaultHandler());
			server.setHandler(chatWebSocketHandler);

			TickerFlasher tickerFlasher = new TickerFlasher(chatWebSocketHandler.webSockets);
			tickerFlasher.start();
			
			
			server.start(); 
			server.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * ContextHandlerCollection contexts = new ContextHandlerCollection();
		 * 
		 * contexts.setHandlers(new Handler[] { new
		 * AppContextBuilder().buildWebAppContext()});
		 * 
		 * JettyServer jettyServer = new JettyServer();
		 * jettyServer.setHandler(contexts); try { jettyServer.start(); } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

}

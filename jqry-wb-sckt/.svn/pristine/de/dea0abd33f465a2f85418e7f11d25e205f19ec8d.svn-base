/**
	TicketFlasher.java
	Author @ Suresh Govindaraj
	Epro Technologies
 **/

package com.epro.ws;

import java.util.Set;

import com.google.gson.Gson;
 

public class TickerFlasher extends Thread {
 
	private Set<ChatWebSocket> webSockets;
	private double price[] = {60.34,61.23,45.01,34.76,50.34,49.62};
	private boolean tt;
	private PatientService patientService;
	private Gson gson;
	public TickerFlasher(Set<ChatWebSocket> webSockets2) {
		this.webSockets = webSockets2;
		this.patientService = new PatientService();
		this.gson = new Gson();
	}

	@Override
	public void run() {
		
		 int pCount = 0;
		 
		 while(true){
			  
			 try {
				 synchronized (webSockets) {
					 
					 if(pCount > 5)
						 pCount = 0;
					 
					 
					 String msg = "infy";
					 
					 if(tt){
						 msg = "msft";
						 tt = false;
					 }
					 else{
						 tt = true;
					 }
					 
					 for (ChatWebSocket webSocket : ChatWebSocketHandler.webSockets) {
					     // send a message to the current client WebSocket. 
						 patientService.change();
					     webSocket.connection.sendMessage(gson.toJson(patientService.getAll())); 
					 }
					 
					 //System.out.println("Update Sent...");
					 pCount++;
					
				 } 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}

}



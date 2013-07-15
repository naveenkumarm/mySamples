package com.epro.ws;

import java.io.IOException;
import java.net.URI;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClient;


/* ------------------------------------------------------------ */
/** WebSocket Example Chat client.
 * <p>This class is a example of the Jetty WebSocket client API to
 * create a load tester for the simple {@link ChatServlet}.
 * It create a number of WebSocket chat connections and then picks random
 * connections to send messages on.   The received messages are simply counted.
 */
public class ClientSocket implements WebSocket.OnTextMessage
{
    private static final AtomicLong sent = new AtomicLong(0);
    private static final AtomicLong received = new AtomicLong(0);
    private static final Set<ClientSocket> members = new CopyOnWriteArraySet<ClientSocket>();
    private final String name;
    private final Connection connection;
    
    
    /* ------------------------------------------------------------ */
    /** Construct a Chat Load Client
     * @param username The username of the client
     * @param client The WebSocketClient to use for the connection.
     * @param host The host to connect to
     * @param port The port to connect to
     * @throws Exception
     */
    public ClientSocket(String username,WebSocketClient client,String host, int port)
    throws Exception
    {
        name=username;
        connection=client.open(new URI("ws://"+host+":"+port+"/chat"),this).get();
    }
    
    /* ------------------------------------------------------------ */
    /** Send a chat message from the user
     * @param message the message to send
     * @throws IOException
     */
    public void send(String message) throws IOException
    {
        connection.sendMessage(name+":"+message);
        sent.incrementAndGet();
    }
    
    /* ------------------------------------------------------------ */
    /** Callback on successful open of the websocket connection.
     */
    public void onOpen(Connection connection)
    {
        //members.add(this);
    }

    /* ------------------------------------------------------------ */
    /** Callback on close of the WebSocket connection
     */
    public void onClose(int closeCode, String message)
    {
       // members.remove(this);
    }

    /* ------------------------------------------------------------ */
    /** Callback on receiving a message
     */
    public void onMessage(String data)
    {
       // received.incrementAndGet();
        System.out.println(data);
    }
    
    /* ------------------------------------------------------------ */
    /** Disconnect the client
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
	public void disconnect() throws IOException
    {
       // connection.disconnect();
    }

    /* ------------------------------------------------------------ */
    /** Main method to create and use ChatLoadClient instances.
     * <p>The default is to connection to localhost:8080 with 1000 clients and to send 1000 messages.
     * @param arg The command line arguments are [ host [ port [ clients [ messages ]]]]. The default is to connection to localhost:8080 with 1000 clients and to send 1000 messages.
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    
    
    
	public static void main(String[] arg) throws Exception
    {
        String host="localhost";
        int port=7070;
        int clients=1100;
        
        
        
        
        
        int mesgs=3;
        
        WebSocketClient client = new WebSocketClient();
        //client.setBufferSize(4096);
        client.setMaxIdleTime(30000);
        client.setProtocol("chat");
       // client.start();
        
     
        ClientSocket[] chat = new ClientSocket[clients];
        for (int i=0;i<chat.length;i++)
            chat[i]=new ClientSocket("user"+i,client,host,7070);
       
    }
    
}

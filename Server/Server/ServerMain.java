// Name :Sidiya Sidiya 
//Instructor:  Dr Gary Newell  
//Program #2 
// due 4/22/2015
//This program will deal with multithread letting 4 user to connect on the same time and 
//playing the same game .
// the server will handle this issue and let one user to start and still waiting for another
//player to show up and make sure the total of player not exceed 4.


package Server;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerMain {
	static int maxCon=0;
	
	public static ArrayList<Socket> sockets=new ArrayList<Socket>();// to add the created socket
	public static ArrayList<String > ids =new ArrayList<String>();//to add the ids of the sockets 
	public static ServerSocket server;// cretaing a server
	public static  void main(String []args ) throws IOException{
		
 		server=new ServerSocket(8889);// specifying the port
 		// loop while the number of user less than the maximum allowed number
 		while(true){
 		Socket socket=server.accept();// accept when the user make a connection
   		sockets.add(socket);
   		
   		
   		

 		}
		
	}
	 

}


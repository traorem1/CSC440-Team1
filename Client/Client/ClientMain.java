// Name :Sidiya Sidiya 
//Instructor:  Dr Gary Newell  
//Program #2 
// due 4/22/2015
//This program will deal with multithread letting 4 user to connect on the same time and 
//playing the same game .
// the server will handle this issue and let one user to start and still waiting for another
//player to show up and make sure the total of player not exceed 4.

//Import some useful libraries and classes
package Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.zip.Inflater;
import java.util.zip.DataFormatException;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		String server = "localhost";
		String output = "";
		String fileToRequest = "magic.txt";//TODO: add code to properly determine and set this
		
		InputStreamReader inclient = null;
		PrintWriter outclient;
		Socket socketclient = new Socket(server, 8889);// client make connection
 		// used to send answers to server
		outclient = new PrintWriter(socketclient.getOutputStream(),true);// to send things to server
		inclient = new InputStreamReader(socketclient.getInputStream());//get things from server
		BufferedReader BR = new BufferedReader(inclient);// read what sent from server
		
		output = "GET /" + fileToRequest + "HTTP/1.1\r\n" + "Host: " + server + "\r\n\r\n";
		outclient.print(output);
		outclient.flush();
		
		while(true){//talk to server replace true with something appropriate
			//talk to server + get file logic
			break;//get rid of this. this is here to prevent code out of reach error message
		}
		
		
		socketclient.close();
	}

 }

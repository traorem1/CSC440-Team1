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
		String input = "";
		
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
		
		while((input = BR.readLine()) != null){
			int length = Integer.MAX_VALUE;//this is to ensure that if length fails to set/be parsed then the reader will read everything in the buffer hopefully retreiving the file anyway
			if (input == "HTTP/1.1 404 Not Found")
			{
				//TODO: handle error
				break;
			}
			else if(input == "HTTP/1.1 200 OK"){
				input = BR.readLine();
				String[] splitHeader = input.split("\\s+");
				try{
				length = Integer.parseInt(splitHeader[splitHeader.length - 1]);
				}
				catch (NumberFormatException badNum)
				{
					length = Integer.MAX_VALUE;//this is to ensure that if length fails to set/be parsed then the reader will read everything in the buffer hopefully retreiving the file anyway
				}
			}
			else if(input == "Content-Type: text/plain")
			{
				String file = "";
				char[] buf = null;
				BR.read(buf, 0, length);//TODO: remove any leading new lines, they might cause this line to trim the end of the file examine this and use offset to skip starting new lines if any
				file = buf.toString();
			}
			
		}
		
		
		socketclient.close();
	}

 }

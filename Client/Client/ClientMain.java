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
import java.util.Scanner;
import java.util.zip.Inflater;
import java.util.zip.DataFormatException;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		InputStreamReader inclient = null, inclient1;
		PrintWriter outclient;
		Socket socketclient;
 		socketclient = new Socket("localhost", 8889);// client make connection
 		// used to send answers to server
		outclient = new PrintWriter(socketclient.getOutputStream(),true);// to send things to server
		inclient = new InputStreamReader(socketclient.getInputStream());//get things from server
		BufferedReader BR = new BufferedReader(inclient);// read what sent from server
		
		Scanner scan = new Scanner(System.in);// get answers from the user
		// loop over all the 10 questions
		while (true) {
 			System.out.print(BR.readLine());
				
		}
		//socketclient.close();

	}

 }

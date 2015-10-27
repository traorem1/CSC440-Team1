// Name :Sidiya Sidiya 
//Instructor:  Dr Gary Newell  
//Program #2 
// due 4/22/2015
//This program will deal with multithread letting 4 user to connect on the same time and 
//playing the same game .
// the server will handle this issue and let one user to start and still waiting for another
//player to show up and make sure the total of player not exceed 4.

//Import some useful libraries and classes
package client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleClient {

	public static void main(String[] args) throws IOException {
		InputStreamReader inclient = null, inclient1;
		PrintWriter outclient;
		Socket socketclient;
		int end = 0;// used to know when the game is over
		int count = 0;// to gurantee the while loop
 		socketclient = new Socket("localhost", 8889);// client make connection
 		// used to send answers to server
		outclient = new PrintWriter(socketclient.getOutputStream(),true);// to send things to server
		inclient = new InputStreamReader(socketclient.getInputStream());//get things from server
		BufferedReader BR = new BufferedReader(inclient);// read what sent from server
		
		Scanner scan = new Scanner(System.in);// get answers from the user
		// loop over all the 10 questions
		while (count < 10) {

			String r = BR.readLine();// read the question l;ine by line
			System.out.println(r);
			// let the user enters his/her answers
 			System.out.print("Enter your answer as 3 numbers here  ");
			String s = scan.nextLine();

			outclient.println(s);// send the answer to the server to verify it
			 
			r= BR.readLine();
			System.out.println(r);
			// when the game is over
			if (end == 9)
				System.out.println(" Game is over !"
						+ " Thanks for being part of it ");
			count++;
			end++;

 
		}

	}

}

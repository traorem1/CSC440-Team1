// Name :Sidiya Sidiya 
//Instructor:  Dr Gary Newell  
//Program #2 
// due 4/22/2015
//This program will deal with multithread letting 4 user to connect on the same time and 
//playing the same game .
// the server will handle this issue and let one user to start and still waiting for another
//player to show up and make sure the total of player not exceed 4.

package server;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DoComms extends Thread {
	private Socket toClient;

	DoComms(Socket c) {
		this.toClient = c;
	}
// this method run will run every time when any thread is created 
	public void run() {
		try {
			int currentScore = 0;
			int totalScore = 0;
			char wrongAnswer;
           // some empty strings will be needed later 
			String s1 = "";
			String s2 = "", s3 = "", s4 = "", s5 = "", s6 = "", s7 = "";
			// redaing file while waitnig for the client visit
			Scanner sc = new Scanner(new File("f1.txt"));
			PrintWriter Out = new PrintWriter(toClient.getOutputStream(),true);//send things to server
			// get things from server
			InputStreamReader In = new InputStreamReader(
					toClient.getInputStream());
			BufferedReader BR = new BufferedReader(In);
			for (int i = 0; i < 10; i++) {

				String stop = "false";// to be used to check if the user enter
										// answer in the right format
				// getting the question as question and 4 possible answer
				s1 = s1 + sc.nextLine();
				s2 = s2 + sc.nextLine();
				s3 = s3 + sc.nextLine();
				s4 = s4 + sc.nextLine();
				s5 = s5 + sc.nextLine();
				s6 = s6 + sc.nextLine();
				s7 = s1 + s2 + s3 + s4 + s5;
				wrongAnswer = s6.charAt(0);// Store the wrong answer to use it
  				Out.println(s7);
 				String S = BR.readLine();
				 
				// make sure that the number include only 1234
				for (int j = 0; j < S.length(); j++) {
					char a = S.charAt(j);
					int value = (int) a;
					int value2 = value - 48;
					if (value2 > 4) {

						Out.println (" YOUR CHOICE CAN BE ONLY 1,2,3,4 ,PREVIOUS QUESTION WON'T BE COUNTED");
						stop = "true";
						break;
						
						
					}
					///////break;
				}
				 
					// checking that no duplicate Numbers in the answer
				if(stop.equals("false")){
					for (int k = 0; k < S.length(); k++) {
						for (int h = k + 1; h < S.length(); h++) {
							if (S.charAt(k) == S.charAt(h)) {

								Out.println ("NO DUPLICATE NUMBERS ARE ALLOWED ,PREVIOUS QUESTION WON'T BE COUNTED ");
								stop = "true";
								break;
							}
							break;
						}
						break;
					}
				}
				 
				// Calculating the user score
				if (stop.equals("false")) {
					for (int n = 0; n < S.length(); n++) {
						if ((S.charAt(n)) == wrongAnswer) {
							currentScore = -250;
							break;
						}

						else
							currentScore = currentScore + 250;
					}
					totalScore = totalScore + currentScore;

					Out.println(" The wrong answer is " + wrongAnswer
							+ " ,Your Current Score is " + currentScore
							+ " and your Total Score is " + totalScore);
					currentScore = 0;
				}
				 
				s1 = "";
				s2 = "";
				s3 = "";
				s4 = "";
				s5 = "";
				s6 = "";
				s7 = "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

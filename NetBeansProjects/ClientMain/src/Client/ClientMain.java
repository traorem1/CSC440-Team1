package Client;
//Import some useful libraries and classes
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		String server = "localhost";
		String output = "";
		String fileToRequest = "test19.txt";//TODO: add code to properly determine and set this
		String input = "";
                String line;
		
		InputStreamReader inclient = null;
		PrintWriter outclient;
		Socket socketclient = new Socket(server, 8889);// client make connection
 		// used to send answers to server
		outclient = new PrintWriter(socketclient.getOutputStream(),true);// to send things to server
                BufferedWriter BW = new BufferedWriter(outclient);
		inclient = new InputStreamReader(socketclient.getInputStream());//get things from server
		BufferedReader BR = new BufferedReader(inclient);// read what sent from server
		
		output = "GET /" + fileToRequest + " HTTP/1.1\r\n" + "Host: " + server + "\r\n\r\n";
                BW.write(output);
                BW.flush();
		/*outclient.print(output);
		outclient.flush();
                outclient.print("null");*/
                //outclient.close();
		
                //System.out.println(BR.readLine());
		//System.out.println("Hello World!");
		//System.out.println(BR.readLine());
		//while((input = BR.readLine()) != null){
                boolean done = true;
		while(true){
                    while ((line=BR.readLine()) != null )
                    {
                         if (line.isEmpty())
                            {
                                 break;
                            }
                            input= input + System.lineSeparator() + line;
                        }
			//System.out.println("while");
			int length = Integer.MAX_VALUE;//this is to ensure that if length fails to set/be parsed then the reader will read everything in the buffer hopefully retreiving the file anyway
			//System.out.println("while loop");
			if (input.contains("HTTP/1.1 404 Not Found"))
			{
				System.out.println("404 Error Found");
				//TODO: handle error
				break;
			}
			else if(input.contains("HTTP/1.1 200 OK")){
				input = BR.readLine();
				System.out.println("202 code");
				String[] splitHeader = input.split("\\s+");
				try{
				length = Integer.parseInt(splitHeader[splitHeader.length - 1]);
				}
				
				catch (NumberFormatException badNum)
				{
					length = Integer.MAX_VALUE;//this is to ensure that if length fails to set/be parsed then the reader will read everything in the buffer hopefully retreiving the file anyway
				}
                                //read file
				String file = "";
				//char[] buf = null;
				//BR.read(buf, 0, length);//TODO: remove any leading new lines, they might cause this line to trim the end of the file examine this and use offset to skip starting new lines if any
                                //input = "";//blank out input
                                while ((line=BR.readLine()) != null )
                    {
                         
                            input= input + System.lineSeparator() + line;
                            if (line.isEmpty())
                            {
                                 break;
                            }
                        }
                                
				//file = buf.toString();
				System.out.println(input);
				PrintWriter writer = new PrintWriter("output_11092015.txt", "UTF-8");
				writer.println(input);
				writer.close();
                                //TODO: ensure the thread stops here and that all objects are closed first
			}
                        }
			
		}
		
		
		//socketclient.close();
	}

 //}

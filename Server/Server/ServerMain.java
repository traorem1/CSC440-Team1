// Name :Sidiya Sidiya 
//Instructor:  Dr Gary Newell  
//Program #2 
// due 4/22/2015
//This program will deal with multithread letting 4 user to connect on the same time and 
//playing the same game .
// the server will handle this issue and let one user to start and still waiting for another
//player to show up and make sure the total of player not exceed 4.


package Server;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.Deflater;

@SuppressWarnings("unused")
public class ServerMain {
	static int maxCon=0;
	
	public static ArrayList<Socket> sockets=new ArrayList<Socket>();// to add the created socket
	public static ArrayList<String > ids =new ArrayList<String>();//to add the ids of the sockets 
	public static ServerSocket server;// cretaing a server
	public static  void main(String []args ) throws IOException{
		
 		/*server=new ServerSocket(8889);// specifying the port
 		// loop while the number of user less than the maximum allowed number
 		while(true){
 		Socket socket=server.accept();// accept when the user make a connection
   		sockets.add(socket);
   		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
   		out.println("Hello World!");*/
   		//socket.getOutputStream().write("Hello World!".getBytes("US-ASCII")); // or UTF-8 or any other applicable encoding...
		 
   		
   		
   		//test multi threaded below
   		int i=0;

   	    try{
   	      ServerSocket listener = new ServerSocket(8889);
   	      Socket server;

   	      while(true){//perhaps replace true with a variable
   	        doComms connection;

   	        server = listener.accept();
   	        doComms conn_c= new doComms(server);
   	        Thread t = new Thread(conn_c);
   	        t.start();
   	      }
   	    } catch (IOException ioe) {//replace this with call to logging thread
   	      System.out.println("IOException on socket listen: " + ioe);
   	      ioe.printStackTrace();
   	    }
   	//close streams
   		//socket.close();
   		//out.close();
   	  }
   		/*
   		DataInputStream d = new DataInputStream(in);
 
 with: 
     BufferedReader d
          = new BufferedReader(new InputStreamReader(in));

*/
   		
   				
 		
	}

class doComms implements Runnable {
    private Socket server;
    private String line,input;

    doComms(Socket server) {
      this.server=server;
    }

    public void run () {
    	
      input="";
      
      try {
        // Get input from the client
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintStream out = new PrintStream(server.getOutputStream());

        while((line = in.readLine()) != null) {
          input=input + line;
        }
        System.out.println(input);
        
        String[] header = input.split("\\s+");
        File f = new File(header[1].substring(1));//header[1].substring(1);//removes the /
        if(f.exists() && !f.isDirectory()) { 
        	/*DataInputStream stream = new DataInputStream(new FileInputStream(f));
        	stream.readFully(Files.readAllBytes(Paths.get(input)));*/
        	line = "HTTP/1.1 200 OK" + "\r\n" + "Content-Length: " + (int) f.length() + "\r\n";
        	line += "Content-Type: text/plain" + "\r\n\r\n";
        	out.print(line);
        	out.flush();
        	out.println(new String(Files.readAllBytes(Paths.get(input))));
        	out.flush();
        }
        else{
        	out.print("HTTP/1.1 404 Not Found\r\n\r\n");
        	out.flush();
        	//logging here
        }
        

        /*System.out.println("Overall message is:" + input);
        out.println("Overall message is:" + input);*/
        
        
        in.close();
        out.close();
        server.close();
      } catch (IOException ioe) {//replace with passing to Logging Thread
        System.out.println("IOException on socket listen: " + ioe);
        ioe.printStackTrace();
      }
    }
	 

}


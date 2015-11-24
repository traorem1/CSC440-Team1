package Server;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerMain {
    
	public static  void main(String []args ) throws IOException{

   	    try{
   	      ServerSocket listener = new ServerSocket(8889);
   	      Socket server;

   	      while(true){//perhaps replace true with a variable
   	        doComms connection;
   	        /*
   	         * see slides 9 number 29 and implement that for the file 
   	         * generator
   	         */
   	        server = listener.accept();
                //PrintStream out = new PrintStream(server.getOutputStream());
                //out.println("Arch Linus Rocks!");
                //out.flush();
   	        doComms conn_c= new doComms(server);
   	        Thread t = new Thread(conn_c);
   	        t.start();
   	      }
   	    } catch (IOException ioe) {//replace this with call to logging thread
   	      System.out.println("IOException on socket listen: " + ioe);
   	      ioe.printStackTrace();
   	    }
   	  }
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
        out.flush();
        boolean done = true;
        //System.out.println(in.readLine());
        while ((line=in.readLine()) != null ){
            if (line.isEmpty())
            {
                break;
            }
            input+=line;//I know this trims off the newline characters, but don't fix it
        }
        
        System.out.println(input);
        
        String[] header = input.split("\\s+");
        File f = new File(header[1].substring(1));//header[1].substring(1);//removes the /
        
        //uncomment the next two lines to get the folder where the server will check for the files.
        
        /* String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);
 String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);*/
        if(f.exists() && !f.isDirectory()) { 
        	line = "HTTP/1.1 200 OK" + "\r\n" + "Content-Length: " + (int) f.length() + "\r\n";
        	line += "Content-Type: text/plain" + "\r\n\r\n";
        	out.print(line);
        	out.flush();
        	out.println(new String(Files.readAllBytes(Paths.get(f.getPath()))));
        	out.flush();
        }
        else{
        	out.print("HTTP/1.1 404 Not Found\r\n\r\n");
        	out.flush();
        	//logging here
        }
        
        in.close();
        out.close();
        server.close();
      } catch (IOException ioe) {//replace with passing to Logging Thread
        System.out.println("IOException on socket listen: " + ioe);
        ioe.printStackTrace();
      }
    }
	 

}


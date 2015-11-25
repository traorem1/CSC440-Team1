package Server;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {
    
	public static void main(String []args ) throws IOException{
            Map<String, String> cache = new HashMap<String, String>();
            
            File fileName = new File(".");
            File[] fileList = fileName.listFiles();
            for (int i = 0; i < 16; i++) {
                if(fileList[i].getName().contains("test") && !fileList[i].isDirectory())
                {
                    cache.put(fileList[i].getName(), new Scanner(fileList[i]).next());
                }
            }
            
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
   	        doComms conn_c= new doComms(server,cache);
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
    Map fileCache;

    doComms(Socket server, Map files) {
      this.server=server;
      this.fileCache=files;
    }

    public void run () {
    	SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        String startTime = sdfTime.format(now);
        
        int size = 0;
        String cacheResult = "";
        int statusCode = 0;
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
        
        //System.out.println(input);
        
        String[] header = input.split("\\s+");
        File f = new File(header[1].substring(1));//header[1].substring(1);//removes the /
        
        if (fileCache.containsKey(f.getName()))
        {
            cacheResult = "hit";
            size = (int) fileCache.get(f.getName()).toString().length();
            statusCode = 200;
            line = "HTTP/1.1 200 OK" + "\r\n" + "Content-Length: " + size + "\r\n";
        	line += "Content-Type: text/plain" + "\r\n\r\n";
        	out.print(line);
        	out.flush();
        	out.println(fileCache.get(f.getName()));
        	out.flush();
        }
        else if(f.exists() && !f.isDirectory()) {
                cacheResult = "miss";
                size = (int) f.length();
                statusCode = 200;
        	line = "HTTP/1.1 200 OK" + "\r\n" + "Content-Length: " + size + "\r\n";
        	line += "Content-Type: text/plain" + "\r\n\r\n";
        	out.print(line);
        	out.flush();
        	out.println(new String(Files.readAllBytes(Paths.get(f.getPath()))));
        	out.flush();
        }
        else{
                statusCode = 404;
        	out.print("HTTP/1.1 404 Not Found\r\n\r\n");
        	out.flush();
        }
        now = new Date();
        String endTime = sdfTime.format(now);
        
        this.print("localhost/" + f.getName(), startTime, endTime, size , cacheResult, statusCode);
        
        in.close();
        out.close();
        server.close();
      } catch (IOException ioe) {
        System.out.println("IOException on socket listen: " + ioe);
        ioe.printStackTrace();
      }
    }
    
    private synchronized void print(String url,String startTime, String endTime, int fileSize, String cacheBool, int statusCode)
    {
        String entry = url + " " + startTime + " " + endTime + " " + fileSize + " " + cacheBool + " " + statusCode + System.lineSeparator();
        try {
            Files.write(Paths.get("log.txt"), entry.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(doComms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	 

}


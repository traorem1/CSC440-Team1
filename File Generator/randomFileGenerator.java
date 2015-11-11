import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class randomFileGenerator {
	
	static String [] file_Array = new String[100];

	public static void main(String[] args) throws IOException 
	{
		
		final String alphabet = "abcdefghijklmnopqrstuvwxyz";
		final int N = alphabet.length();
		Random rand = new Random();
        BufferedWriter output = null;
        getFiles();
        
        try 
        {
        	
        	for (int i = 0; i < 20; i++)
        	{
				String fileName = file_Array[i];
        		File file = new File("C:\\Users\\Kyle\\workspaceB\\Random_File_Generator\\src\\" + fileName + ".txt");
        		output = new BufferedWriter(new FileWriter(file));
        		int size = (rand.nextInt(99000) + 1000);
        		
            	for (int j = 0; j < size; j++) 
   		 		{
            		output.write(alphabet.charAt(rand.nextInt(N)));
            		output.flush();
   		 		}
        	}
            
        } 
        	catch ( IOException e ) 
        	{
        		e.printStackTrace();
        	} 
        	finally 
        	{
        		if ( output != null ) output.close();
        	}  
	}
	
	public static void getFiles()
    {
    	String line = null;
    	int i = 0;
    try 
    {
		File file = new File("C:\\Users\\Kyle\\workspaceB\\Random_File_Generator\\src\\fileGeneration.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer();
		while ((line = br.readLine()) != null) 
		{
			stringBuffer.append(line);
			stringBuffer.append("\n");
			file_Array[i++] = line;
		}
		fileReader.close();
	} 
    	catch (IOException e) 
    	{
    		e.printStackTrace();
    	}
    }
}

package FileGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class randomFileGenerator {
	
	static String [] file_Array;

	public static void main(String[] args) throws IOException 
	{
            Random rand = new Random();
            BufferedWriter output = null;
        
        try 
        {
            File names = new File("fileGeneration.txt");
            BufferedReader reader = new BufferedReader(new FileReader(names));
            String input = "",line;
            input = reader.readLine();
            while ((line = reader.readLine()) != null)
            {
                input = input + " " + line;//read file names into string
            }
            file_Array = input.split("\\s+");//splits the file names into an array
        	for (int i = 0; i < file_Array.length; i++)
        	{
                    File file = new File(file_Array[i] + ".txt");
                    output = new BufferedWriter(new FileWriter(file));
                    int size = (rand.nextInt(99000) + 1000);
        		
            	for (int j = 0; j < size; j++) 
   		 		{
            		output.write((char)(rand.nextInt(26) + (byte)'a'));
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
}

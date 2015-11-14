

/*
 * Team 1
 writer: Mahamadou TRAORE
 Description:
 The web file generator is used to generate web files. Specifically, the web file generator  will:
 generate random files based on an input file called fileGeneration.txt. The requests in fileGeneration.txt 
 are the requests that your web server can service. Please generate one file for each request in fileGeneration.txt;  
 The size of the random file ranges from 1,000 bytes to 100,000 bytes. The content of the file is random letters from 'a' to 'z'.
*/

import java.io.*;
import java.util.Random;

public class RandomFilesGenerator {
 
    // default min value
    static final int MIN_SIZE   = 1000;
    // default max value
    static final int MAX_SIZE   = 100000;
    // default folder
    static final String FOLDER   = "F:\\";//Thinking about something like localhost...
    // default folder
    static final String TYPE   = ".html";
    //Keeps track of the lines read from the file
    private static String [] lines = new String[1024];

    public static void main(String[] args) throws IOException {
  
      String fileGenerationName = "F:\\fileGeneration.txt";
      int numberOfFiles = 0, k = 0, i = 0;

      BufferedWriter outputFile = null;
      int fileSize = MIN_SIZE;
      File myFile;

      char[] myAlphabet = new char[26];
      
      //Initialize myAlphabet
      for(i = 0; i < 26; i++)
        myAlphabet[i] = (char)(97 + (k++));
    
      //Create a seed for the random file generation
      Random r = new Random(System.currentTimeMillis());
      
      createFile(fileGenerationName);

      //Get the nber of files from the file generation file
      LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(fileGenerationName)));
      lnr.skip(Long.MAX_VALUE);
      numberOfFiles = lnr.getLineNumber() + 1; 
      lnr.close();
      
      //Writes the files with random sizes and random letters from myAlphabet
      for (i = 0; i < numberOfFiles; i++) {
        myFile = new File(FOLDER + lines[i] + TYPE);
        outputFile = new BufferedWriter(new FileWriter(myFile));
        fileSize = (r.nextInt(MAX_SIZE) + MIN_SIZE);
          
        for (int j = 0; j < fileSize; j++) {
          outputFile.write(myAlphabet[r.nextInt(myAlphabet.length)]);
          outputFile.flush();
        }
        //For test purpose
        System.out.println(myFile.getName());
      }

      //Closes opened BufferWriter
      if ( outputFile != null ) 
        outputFile.close();
    }
 

 /*
     * This function creates files using the data
     from the file generation file
 */
 public static void createFile(String fileGenerationName) throws IOException {
   //Variables
   String line = null;
   int i = 0;
  
   BufferedReader buffer = new BufferedReader(new FileReader(new File(fileGenerationName)));
   StringBuffer stringBuffer = new StringBuffer();
  
   while ((line = buffer.readLine()) != null) {
     stringBuffer.append(line + "\n");
     lines[i++] = line;
   }
   buffer.close();
 } 
}
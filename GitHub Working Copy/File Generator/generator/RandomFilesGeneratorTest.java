package generator;
/*
 Team 1
 writer: Mahamadou TRAORE
 Description:
 Simple JUnit test class
*/
import static org.junit.Assert.*;
import java.io.*;
import org.junit.Test;

public class RandomFilesGeneratorTest {
	private long fileSize = 0;
	private int i = 0;
	
	@Test
	/*Test case for files created from the fileGeneration file*/
	public void createFiles() throws Exception {
		RandomFilesGenerator junit = new RandomFilesGenerator();
		String file = RandomFilesGenerator.FOLDER + "fileGeneration.txt";
		junit.createFiles(file);
		
		for (i = 1; i <= 20; i++) {
			assertTrue((new File(RandomFilesGenerator.FOLDER + "test" + i + ".html")).exists());
		}
	}
	
	@Test
	/*Test case for the size requirement*/
	public void generateFiles() throws Exception {
		RandomFilesGenerator junit = new RandomFilesGenerator();
		junit.generateFiles();
		
		for (i = 1; i <= 20; i++) {
			fileSize = (new File(RandomFilesGenerator.FOLDER + "test" + i + ".html")).length();
			
			assertTrue(fileSize > RandomFilesGenerator.MIN_SIZE &&
					fileSize < RandomFilesGenerator.MAX_SIZE);
		}
	}
	

}

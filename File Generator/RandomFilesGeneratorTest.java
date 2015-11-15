/*
 Team 1
 writer: Mahamadou TRAORE
 Description:
 Simple JUnit test class
*/
import static org.junit.Assert.*;

import org.junit.Test;

public class RandomFilesGeneratorTest {

	@Test
	public void GenerateFiles() throws Exception {
		RandomFilesGenerator junit = new RandomFilesGenerator();
		boolean value = junit.generateFiles();
		assertEquals(true, value);
	}

}

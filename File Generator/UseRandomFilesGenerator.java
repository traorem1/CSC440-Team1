/*
 Team 1
 writer: Mahamadou TRAORE
 Description:
 Simple class to use the RandomFilesGenerator class
*/
public class UseRandomFilesGenerator {
  public static void main(String[] args) throws Exception {
    RandomFilesGenerator myFileGenerator = new RandomFilesGenerator();
    myFileGenerator.generateFiles("F:\\fileGeneration.txt");
  }
}
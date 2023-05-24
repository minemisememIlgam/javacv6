import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileTransformer {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("Chybný počet argumentů. Použití: java FileTransformer <vstupní_soubor1> <vstupní_soubor2>");
      return;
    }

    String inputFile1 = args[0];
    String inputFile2 = args[1];

    String outputFile1 = getOutputFileName(inputFile1);
    String outputFile2 = getOutputFileName(inputFile2);

    try {
      transformFile(inputFile1, outputFile1);
      transformFile(inputFile2, outputFile2);
      System.out.println("Transformace souborů dokončena.");
    } catch (IOException e) {
      System.out.println("Chyba při transformaci souborů: " + e.getMessage());
    }
  }

  private static String getOutputFileName(String inputFile) {
    String currentDir = System.getProperty("user.dir");
    String fileName = new File(inputFile).getName();
    String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
    String extension = fileName.substring(fileName.lastIndexOf('.'));
    String newFileName = baseName + "_final" + extension;
    return currentDir + File.separator + newFileName;
  }

  private static void transformFile(String inputFileName, String outputFileName) throws IOException {
    // Vytvoření kopie s jiným názvem
    Files.copy(new File(inputFileName).toPath(), new File(outputFileName).toPath(),
        StandardCopyOption.REPLACE_EXISTING);
  }
}
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Data {
	
	public static String dataFolder = System.getProperty("user.home") + "/gamedata/dat/";
	
	public static File datFile = new File(dataFolder + "data.txt");
	public static File dataBackupFile = new File(dataFolder + "defaultdata.txt");
	
	public static int findIndexOf(String value, File dataFile) throws IOException {
		for(int i = 0; i <= countLines(dataFile); i++) {
			String title = getValueLineIndex(i, dataFile).substring(0, getValueLineIndex(i, dataFile).indexOf(':'));
			if(title.equalsIgnoreCase(value))
			return i;
		}
		return -1;
	}
	
	public static String extract(int lineIndex, File dataFile) throws IOException {
		String result = getValueLineIndex(lineIndex, dataFile);
		result = result.substring(result.indexOf(':') + 2);
		return result;
	}
	
	public static int extractInt(int lineIndex, File dataFile) throws IOException {
		String result = getValueLineIndex(lineIndex, dataFile);
		result = result.substring(result.indexOf(':') + 2);
		return Integer.valueOf(result);
	}
	
	public static void resetDataFile() throws IOException {
		for(int i = 0; i < countLines(datFile); i++) {
			setValueOnLineIndex(i, datFile, getValueLineIndexBackup(i));
		}
	}
	
	public static int countLines(File dataFile) {

		Path path = Paths.get(dataFile.getPath());
		int lines = 0;
		try {
			lines = (int) Files.lines(path).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;

	}
	
	public static void printFile(File dataFile) throws IOException {
		for(int i = 0; i < countLines(dataFile); i++) {
			System.out.println(getValueLineIndex(i, dataFile));
		}
	}
	
	public static void setValueOnLineIndex(int n, File dataFile, String text) throws IOException {
		String fileText = "";
		for(int i = 0; i < countLines(dataFile); i++) {
			if(i != n) fileText += getValueLineIndex(i, dataFile) + "\n";
			else fileText += text + "\n";
		}
		System.out.println(fileText);
		PrintWriter writer = new PrintWriter(dataFile);
		writer.print(fileText);
		writer.close();
	}

	public static String getValueLineIndex(int n, File dataFile) throws IOException {
		try {
			String line = Files.readAllLines(Paths.get(dataFile.getPath())).get(n);
	        return line;
	    } 
	    catch (IOException e) {
	    	System.out.println("Error: Data File not found.");
	    	dataFile.createNewFile();
	    	resetDataFile();
	    	return getValueLineIndex(n, dataFile);
	    }
	}
	
	public static String getValueLineIndexBackup(int n) throws IOException {
		
		try {
			String line = Files.readAllLines(Paths.get(dataBackupFile.getPath())).get(n);
	        return line;
	    } 
	    catch (IOException e) {
	    	System.out.println("Error: Data Backup File not found.");
	    	return getValueLineIndexBackup(n);
	    }
		
	}
	
}

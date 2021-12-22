import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Launcher {

	public static void main(String[] args) throws IOException, InterruptedException {
		

//		if(
//		installationFunctional()
//		)
//		Program.run();
	}
	
	public static boolean installationFunctional() throws IOException {
		
		System.out.println("Checking installation.");
		
		//make sure all files are there, readable, and in proper format
		//c:/Users/[user]/Documents/gamedata/dat
		File datadottxt = Program.userData; //global userdata, like balance and level
		File backupdatadottxt = Program.userData; //placeholder. to lazy to delete this stuff
		File itemsdottxt = Program.itemsList; //list of items, to prevent modifications
		
		//c:/Users/[user]/Documents/gamedata/dat/items
		ArrayList<File> itemList = new ArrayList<File>();
		int correctLines = 0;
		for(int i = 0; i < Data.countLines(itemsdottxt); i++) {
			itemList.add(new File(Program.dataFolder + "items/" + Data.getValueLineIndex(i, itemsdottxt) + ".txt"));
			if(itemList.get(i).exists()) correctLines++;
		}
		
		return datadottxt.exists() && backupdatadottxt.exists() && itemsdottxt.exists() && itemList.size() == correctLines;
		
	}
	
}

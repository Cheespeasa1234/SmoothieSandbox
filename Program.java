import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
	
		public static int BALANCE;
		public static int LEVEL;
		public static Scanner s = new Scanner(System.in);
		public static String dataFolder = Data.dataFolder;
		
		public static File userData = Data.datFile;
		public static File itemsList = new File(Data.dataFolder + "items.txt");

		public static Item[] items = new Item[Data.countLines(itemsList)];
		
		public static void run() throws IOException, InterruptedException {
			extractData();
		}
		
		public static void extractData() throws IOException, InterruptedException {
			System.out.println("Begin loading data.");
			long start = System.nanoTime();
			BALANCE = Data.extractInt(Data.findIndexOf("balance", userData), userData);
			LEVEL = Data.extractInt(Data.findIndexOf("level", userData), userData);
			System.out.println("$" + BALANCE + " LEVEL " + LEVEL);
			for(int i = 0; i < Data.countLines(itemsList); i++) {
				items[i] = (assemble(i));
				System.out.println(items[i].toString());
			}
			long elapsed = (System.nanoTime() - start) / 1000000;
			System.out.println("Data Loaded. " + elapsed + " ms elapsed.");
		}
		
		public static Item assemble(int i) throws IOException {
			String fileName = dataFolder + "/items/"+ Data.getValueLineIndex(i, itemsList) + ".txt";
			File dat = new File(fileName);
			int itemPrice = Data.extractInt(Data.findIndexOf("price", dat), dat);
			int itemQuantity = Data.extractInt(Data.findIndexOf("quantity", dat), dat);
			int itemLevel = Data.extractInt(Data.findIndexOf("leveltounlock", dat), dat);
			String itemClass = Data.extract(Data.findIndexOf("class", dat), dat);
			String itemPlural = Data.extract(Data.findIndexOf("pluralname", dat), dat);
			String itemName = Data.getValueLineIndex(i, itemsList);
			return new Item(itemQuantity, itemPrice, itemLevel, itemName, itemPlural, itemClass);
		}
		
		public static boolean unlocked(int status) {
			return status == 2;
		}
		public static boolean unlocked(Item item) {
			return item.lockStatus == 2;
		}
				
}

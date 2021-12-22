public class Item {
	
	public int owned = 0;
	public int price = 0;
	public int levelToUnlock = 0;
	
	public int lockStatus;
	
	//0: locked
	//1: available to unlock
	//2: unlocked
	
	public String category = "Item";
	public String name = "Item";
	public String plural = "Items";
	
	public Item(int invAmount, int cost, int levelUnlock, String itemName, String pluralName, String itemClass) {
		owned = invAmount;
		price = cost;
		levelToUnlock = levelUnlock;
		name = itemName;
		plural = pluralName;
		category = itemClass;
		if(Program.LEVEL >= levelToUnlock) lockStatus = 1;
		else lockStatus = 0;
	}
	
	public String toString() {
		return "Name: " + name + ". Plural: " + plural + ". Owned: " + owned + ". Price: " + price + ". levelToUnlock: " + levelToUnlock + ". lockStatus: " + lockStatus + ". Category: " + category + ".";
	}
	
}

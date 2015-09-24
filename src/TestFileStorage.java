import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * This java class is for TESTING PURPOSES for  file storage
 * THIS IS NOT INTENDED FOR OUR MAIN PROGRAM!!!!
 * @author Tang Wei Ren
 */
public class TestFileStorage {
	public static FileStorage file;
	public static FileData data;
	
	public void main(){
		
		
		
		file = new FileStorage();
		data = new FileData();
		//file.add("myName");
		//file.setFilePath("C:\\Users\\TWR\\Desktop\\tekong.txt");
		//file.add("pokemon");
		
		Scanner sc = new Scanner(System.in);
		boolean isActive = false;
		String cmd, input;
		
		do{
			System.out.print("Command: ");
			cmd = sc.next();
			input = sc.nextLine();
			isActive = executeCmd(cmd, input);
		}while(isActive);
		
	}
	
	public static boolean executeCmd(String cmd, String input)
	{
		
		input = trim(input);
		
		//System.out.println("'" + input + "'");
		//System.out.println(file.getFilePath());
		switch(cmd){
			case "add": 
				file.write(input);
				return true;
			
			case "delete":
				if(data == null){
					data = file.getData();
					System.out.println("Got data");
				}
				file.delete(input,data);
				return true;
			
			case "display":
				data = file.display();
				printFileOutput();
				return true;
				
			case "search":
				data = file.search(input);
				printFileOutput();			
				return true;
			
			case "path":
				file.setFilePath(input);
				return true;
			
			case "exit":
				return false;
		
			default: 
				System.out.println("Invalid command. Type 'exit' to quit");
				return true;			
		}
		
	}
	public static String trim(String str){		
		
		int index = str.indexOf(" ");	
		str = str.substring(index+1);		
		return str;
	}
	
	public static void printFileOutput(){
		HashMap<Integer, String> map = data.getDisplayMap(); 
		
		Set set = map.entrySet();
	      // Get an iterator
	      Iterator i = set.iterator();
	      // Display elements
	      while(i.hasNext()) {
	         Map.Entry me = (Map.Entry)i.next();
	         System.out.print(me.getKey() + ": ");
	         System.out.println(me.getValue());
	      }
	      System.out.println("---------------------------------------");
	      HashMap<String, Integer> map1 = data.getOriginalMap(); 
	      Set set1 = map1.entrySet();
	      // Get an iterator
	      Iterator ii = set1.iterator();
	      // Display elements
	      while(ii.hasNext()) {
	         Map.Entry me = (Map.Entry)ii.next();
	         System.out.print(me.getValue() + ": ");
	         System.out.println(me.getKey());
	      } 
	}
}

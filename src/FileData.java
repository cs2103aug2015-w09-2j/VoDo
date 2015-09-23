import java.util.TreeMap;


public class FileData {

	private TreeMap<String, Integer> originalMap;
	private TreeMap<Integer, String> displayMap;
	
	public FileData(){
		
	}
	public FileData(TreeMap<String, Integer> originalMap, TreeMap<Integer, String> displayMap){
		this.originalMap = originalMap;
		this.displayMap = displayMap;
	}
	
	public TreeMap<String, Integer> getOriginalMap(){
		return originalMap;
	}
	
	public TreeMap<Integer, String> getDisplayMap(){
		return displayMap;
	}
}

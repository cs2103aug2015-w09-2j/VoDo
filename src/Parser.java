import java.text.ParseException;

/***
 * 
 * @author Razali
 *
 */

public class Parser {
	
	

	/*** METHODS ***/
	
	/***
	 * Auxiliary method to get a particular word from a string
	 * @param intIndex The position of the word, beginning from '0'
	 * @param strText The string from which to get the word
	 * @return The word at position intIndex
	 * @exceptions If intIndex exceeds the bounds, IndexOutOfBoundsException is thrown
	 */
	private static String getWord(int intIndex, String strText){
		String[] words = strText.split(" ");
		
		if(intIndex < 0 || intIndex >= words.length)
			throw new IndexOutOfBoundsException();
		
		return words[intIndex];
	}
	
	private static String removeNWords(int numOfWordsToRemove, String strText){
		int intRemoved = 0;
		String strWord;
		
		while(intRemoved != numOfWordsToRemove){
			strWord = getWord(0, strText);
			strText = strText.replace(strWord, "").trim();
			intRemoved++;
		}
		
		return strText;
	}
	
	private static int getNumberOfWords(String strText){
		return strText.split(" ").length;
	}
	
	public static Task parse(String strCommand){
		
		CommandType.Types type = getCommandType(strCommand);
		String strDescription;
		DateClass startDate, endDate;
		
		switch(type){
			
		case ADD_DEADLINE:
			strCommand = removeNWords(2, strCommand);
			strDescription = getDescription(strCommand);
			
			strCommand = removeNWords(getNumberOfWords(strDescription), strCommand);
			
			endDate = getDate(strCommand);
			
			return new DeadLine(strDescription, endDate);
			default:
				return null;
					
		}
	}
	public static void main(String[] args){
		String command = "add -d doing parser is shit job 25/3";
		Task t = parse(command);
	}
	
	
	/***
	 * Given the input command, returns you the type of command.
	 * @param strCommand Input command to parse
	 * @return An enum element from CommandType.Types
	 */
	public static CommandType.Types getCommandType(String strCommand){
		String strFirstWordFromCommand = getWord(0, strCommand);
		strCommand = strCommand.replace(strFirstWordFromCommand, "").trim();
		
		switch(strFirstWordFromCommand){
		
			//After "add", we can have -e(Event) -f(Floating) or -d(DeadLine)
			//We need to parse them by getting the 2nd word
			case "add":
				String strNextWord = getWord(0, strCommand);
				CommandType.TaskTypes taskType = getTaskType(strNextWord);
				
				//Match CommandType.TaskTypes to CommandType.Types
				for(CommandType.Types type: CommandType.Types.values()){
					if(type.toString().equals(taskType.toString())){
						return type;
					}
				}
				//Mismatch
				return CommandType.Types.UNKNOWN;
			
				
			case "delete":
				return CommandType.Types.DELETE;
				
			case "display":
				return CommandType.Types.DISPLAY;
				
			case "update":
				return CommandType.Types.UPDATE;
				
			default:
				return CommandType.Types.UNKNOWN;
			
		}
		
		
	}
	
	
	/***
	 * This function returns the type of task from a given delimiter such as
	 * "-d", "-e" or "-f".   An empty string represents unknown.
	 * @param strDelimeter Comprises of "-d" for deadline, "-e" for event or "-f" for float.
	 * @return Returns the Task type
	 */
	private static CommandType.TaskTypes getTaskType(String strDelimiter){
		for(CommandType.TaskTypes taskType : CommandType.TaskTypes.values()){
			String strTaskType = taskType.toString();
			
			if(strTaskType.equals(strDelimiter)){
				return taskType;
			}
		}
		
		return CommandType.TaskTypes.UNKNOWN;
	}
	
	
	public static String getDescription(String strCommand){
		StringBuilder sb = new StringBuilder();
		int intWordIndex = 0;
		
		String strNextWord = getWord(intWordIndex, strCommand);
		
		while(DateHandler.tryParse(strNextWord) == null){
			sb.append(strNextWord + " ");
			intWordIndex++;
			strNextWord = getWord(intWordIndex, strCommand);
		}
		
		String strDescription = sb.toString().trim();
		
		return strDescription;
	}
	
	
	
	public static DateClass getDate(String strCommand){
		String strDate = getWord(0, strCommand);
		
		strDate = DateHandler.tryParse(strDate);
		
		
		
		try {
			return new DateClass(strDate);
		} catch (NoSuchFieldException | ParseException e) {
			e.printStackTrace();
		} 
		
		return null;
		
	}
	
	public static void getTime(String strCommand){
		return;
	}

	
	
}

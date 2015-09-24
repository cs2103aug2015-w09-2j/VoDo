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
	
	/***
	 * Given the input command, returns you the type of command.
	 * @param strCommand Input command to parse
	 * @return An enum element from CommandType.Types
	 */
	public static CommandType.Types getCommandType(String strCommand){
		String strFirstWordFromCommand = getWord(0, strCommand);
		
		switch(strFirstWordFromCommand){
		
			//After "add", we can have -e(Event) -f(Floating) or -d(DeadLine)
			//We need to parse them by getting the 2nd word
			case "add":
				String strSecondWordFromCommand = getWord(1, strCommand);
				CommandType.TaskTypes taskType = getTaskType(strSecondWordFromCommand);
				
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
			
			if(strTaskType.equals(strDelimiter))
				return taskType;
		}
		
		return CommandType.TaskTypes.UNKNOWN;
	}
	
	
	public static String getDescription(String strCommand){
		return null;
	}
	
	
	
	public static String getDate(String strCommand){
		return null;
	}
	
	public static void getTime(String strCommand){
		return;
	}

	
	
}

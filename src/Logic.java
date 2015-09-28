
public class Logic {
	private static FileStorage fileStorage = new FileStorage();
	private static CommandType.Types command; 
	@SuppressWarnings("unused")
	private static boolean getCommand(String input){
		command = Parser.parse(input);
		if(command == CommandType.Types.UNKNOWN){
			return false;
		}else{
		executeCommand(command,input);
		}
		return true;
	}
	
	private static boolean executeCommand(CommandType.Types command,String input){
		switch(command){
		case ADD_EVENT :
			String eventDescription = Parser.getDescription(input.substring(6));
			DateClass endDate = Parser.getDate(input.substring(6));
			Event event = new Event(eventDescription,endDate);
			writeTaskTofile(event);
			break;
		case ADD_DEADLINE:
			String deadlineDescription = Parser.getDescription(input.substring(6));
			DateClass endDeadline = Parser.getDate(input.substring(6));
			DeadLine deadline = new DeadLine(deadlineDescription,endDeadline);
			writeTaskTofile(deadline);
			break;
		case ADD_FLOATING:
			String floatingDescription = Parser.getDescription(input.substring(6));
			Floating floating = new Floating(floatingDescription);
			writeTaskTofile(floating);
			break;
		case UPDATE:
			break;
		case DELETE:	
			break;
		default:
			break;		
		}
	return true;	
	}

	private static void writeTaskTofile(Task task) {
		String taskType = task.getClass().getName();
		String writeToFile;
		switch(taskType){
		case "Event":
			writeToFile = taskType + task.getDescription() + ((Event) task).getStartDate().toString() + ((Event) task).getEndDate().toString();
			fileStorage.write(writeToFile);
			break;
		case "DeadlLine":
			writeToFile = taskType + task.getDescription() + ((DeadLine) task).getEndDate().toString();
			fileStorage.write(writeToFile);
			break;
		case "Floating":
			writeToFile = taskType + task.getDescription();
			fileStorage.write(writeToFile);
			break;
		}
	}
}


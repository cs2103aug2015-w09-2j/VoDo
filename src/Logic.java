
public class Logic {
	private static CommandType.Types command; 
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
			break;
		case ADD_DEADLINE:
			String deadlineDescription = Parser.getDescription(input.substring(6));
			DateClass endDeadline = Parser.getDate(input.substring(6));
			DeadLine deadline = new DeadLine(deadlineDescription,endDeadline);
			break;
		case ADD_FLOATING:
			String floatingDescription = Parser.getDescription(input.substring(6));
			Floating floating = new Floating(floatingDescription);
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
}


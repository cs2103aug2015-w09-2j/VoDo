
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
			break;
		case ADD_DEADLINE:
			break;
		case ADD_FLOATING:
			break;
		case UPDATE:
			break;
		case DELETE:	
			break;
		}
	return true;	
	}
}


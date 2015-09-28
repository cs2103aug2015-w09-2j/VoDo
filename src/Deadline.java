
public class DeadLine extends Task{
	
	private DateClass endDate;
	
	public DeadLine(String description, DateClass endDate){
		super(description);
		this.endDate = endDate;
	}
	public DateClass getEndDate(){
		return endDate;
	}
}

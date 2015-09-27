
public class Event extends Task{


	private DateClass startDate;
	private DateClass endDate;
	
	public Event(String strDescription, DateClass startDate, DateClass  endDate){
		
		super(strDescription);
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Event(String strDescription, DateClass startDate){
		this(strDescription, startDate, startDate);
	}
	
	public DateClass getStartDate() {
		return startDate;
	}
	public DateClass getEndDate() {
		return endDate;
	}
}

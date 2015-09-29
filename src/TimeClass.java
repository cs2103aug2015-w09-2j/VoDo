

public class TimeClass {
	
	private int intHour;
	private int intMin;
	
	public TimeClass(int hour, int min){
		this.intHour = hour;
		this.intMin = min;
	}
	
	public TimeClass(int hour){
		this(hour, 0);
	}
	
	public int getHour(){
		return intHour;
	}
	
	public int getMin(){
		return intMin;
	}

}

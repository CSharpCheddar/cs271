
public class Guest implements Comparable<Guest> {
	
	private int importance;
	private double tip;
	private String status;
	
	@Override
	public int compareTo(Guest guest) {
		if(getImportance() > guest.getImportance()) {
			return 1;
		} else if(getImportance() < guest.getImportance()) {
			return -1;
		} else {
			return 0;
		}
	}
	//constructor initializes instance variables
	public Guest(int importance, String status) {
		this.importance = importance;
		this.status = status;
		if(status.equals("R")) {
			tip = 9.00;
		} else if(status.equals("I")) {
			tip = 15.00;
		} else if(status.equals("V")) {
			tip = 30.00;
		}
	}
	//accessors
	public int getImportance() {
		return importance;
	}
	
	public double getTip() {
		return tip;
	}
	
	public String getStatus() {
		return status;
	}
	//mutator for tip if guest is skipped
	public void skipped() {
		if(status.equals("R")) {
			tip -= .05;
		} else if(status.equals("I")) {
			tip -= .1;
		}
	}
}
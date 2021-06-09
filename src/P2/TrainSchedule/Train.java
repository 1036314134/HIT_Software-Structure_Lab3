package P2.TrainSchedule;

import P1.Resource;

public class Train extends Resource{
	private final String type;
	private final int seats;
	private final int year;

	protected void checkRep() {
		assert this.getName() != null;
		assert this.getYear() >= 0;
		assert this.getSeats() >= 0;
		assert this.getType() != null;
	}

	//constructor
	private Train(String name, String type, int seats, int year) {
		super(name);
		this.type = type;
		this.seats = seats;
		this.year = year;
		checkRep();
	}

	public static Train getNewTrain(String id, String type, int seats, int year) {
		return new Train(id, type, seats, year);
	}

	//methods
	public String getType() {
		return new String(this.type);
	}

	public int getSeats() {
		return this.seats;
	}

	public double getYear() {
		return this.year;
	}
}

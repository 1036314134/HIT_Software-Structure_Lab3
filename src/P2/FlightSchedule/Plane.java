package P2.FlightSchedule;

import P1.Resource;

public class Plane extends Resource{
	private final String type;
	private final int seats;
	private final double age;
	
	protected void checkRep() {
		assert this.getName() != null;
		assert this.getAge() >= 0;
		assert this.getSeats() >= 0;
		assert this.getType() != null;
	}

	//constructor
	public Plane(String name, String type, int seats, double age) {
		super(name);
		this.type = type;
		this.seats = seats;
		this.age = age;
	}
	
	public static Plane getNewPlane(String name, String type, int seats, double age) {
		return new Plane(name, type, seats, age);
	}
	
	
	//methods
	public String getType() {
		return new String(this.type);
	}

	public int getSeats() {
		return this.seats;
	}

	public double getAge() {
		return this.age;
	}
	
	
}

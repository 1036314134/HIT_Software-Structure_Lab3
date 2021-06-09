package P1;

public class Location {
	private final String name;
	
	protected void checkRep() {
		assert this.name != null;
	}
	
	//constructor
	private Location(String name) {
		this.name = name;
	}
	
	public static Location getNewLocation(String name) {
		return new Location(name);
	}
	
	//method
	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object o) {
		return this.name.equals(((Location) o).getName());
	}
}

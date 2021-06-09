package P1;

public class Location {

	// Abstraction function:
	// 表示项目中出现的位置
	//
	// Representation invariant:
	// name记录位置名，不为空
	//
	// Safety from rep exposure:
	// 所有域均为private,final
	// 使用防御性拷贝
	
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
		return new String(this.name);
	}
	
	@Override
	public boolean equals(Object o) {
		return this.name.equals(((Location) o).getName());
	}
}

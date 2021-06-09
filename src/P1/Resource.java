package P1;

public class Resource {
	private final String name;
	
	protected void checkRep() {
		assert this.name != null;
	}
	
	//constructor
	public Resource(String name) {
		this.name = name;
	}
	
	//observer 
	public String getName() {
		return new String(this.name);
	}

	@Override
	public String toString() {
		return "Resource [name=" + name + "]";
	}
	
	@Override
	public boolean equals(Object resource) {
		Resource source = (Resource) resource;
		return this.toString().equals(source.toString());
	}
}

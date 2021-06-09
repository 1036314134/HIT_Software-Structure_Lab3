package P1;

public class State {
	private String name;
	
	//constructor
	private State(String name) {
		this.name = name;
	}
	
	public static State getNewState(String name) {
		return new State(name);
	}
	
	//methods
	public String getName() {
		return new String(this.name);
	}
	
	/**
	 * 判断目前的状态能否转换到指定状态
	 * @param name 指定状态名
	 * @return 可以返回true， 否则返回false
	 */
	public boolean canchange(String name) {
		switch(name) {
		case "WAITING":
			return false;
		case "ALLOCATED":
			if(this.getName().equals("WAITING")) {
				return true;
			}else {
				return false;
			}
		case "RUNNING":
			if(this.getName().equals("ALLOCATED") || this.getName().equals("BLOCKED")) {
				return true;
			}else {
				return false;
			}
		case "BLOCKED":
			if(this.getName().equals("RUNNING")) {
				return true;
			}else {
				return false;
			}
		case "ENDED":
			if(this.getName().equals("RUNNING")) {
				return true;
			}else {
				return false;
			}
		case "CANCELLED":	
			if(this.getName().equals("WAITING") || this.getName().equals("ALLOCATED") || this.getName().equals("BLOCKED")) {
				return true;
			}else {
				return false;
			}
		default:
			return false;
		}
	}
}

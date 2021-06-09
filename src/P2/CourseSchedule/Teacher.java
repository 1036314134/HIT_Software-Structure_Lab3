package P2.CourseSchedule;

import P1.Resource;

public class Teacher extends Resource{
	private final String id;
	private final String gender;
	private final String work;

	protected void checkRep() {
		assert this.getName() != null;
		assert this.getId() != null;
		assert this.getGender() != null;
		assert this.getWork() != null;
	}

	//constructor
	private Teacher(String name, String id, String gender, String work) {
		super(name);
		this.id = id;
		this.gender = gender;
		this.work = work;
	}

	public static Teacher getNewTeacher(String name, String id, String gender, String work) {
		return new Teacher(name, id, gender, work);
	}

	//method
	public String getId() {
		return new String(this.id);
	}

	public String getGender() {
		return new String(this.gender);
	}

	public String getWork() {
		return new String(this.work);
	}
}

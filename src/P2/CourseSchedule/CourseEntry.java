package P2.CourseSchedule;

import P1.Location;
import P1.PlanningEntry;
import P1.PlanningEntryCollection;
import P1.Time;
import P1.Timeslot;

public class CourseEntry extends PlanningEntryCollection<Teacher>{
	// Abstraction function:
	// 表示数节课，包含老师、教室、上课时间
	
	// Representation invariant:
	// 一节课只有一个时间段和一个教室
	
	// Safety from rep exposure:
	// 所有域均为private final
	// 使用防御性拷贝
	
	//constructor
	private CourseEntry() {
		super();

		checkRep();
	}

	public static CourseEntry getCourseEntry() {
		CourseEntry entry = new CourseEntry();
		return entry;
	}
	
	//methods
	/**
	 * 增加一个老师
	 * @param name 老师名字
	 * @param id 身份证号
	 * @param gender 性别
	 * @param work 职称
	 * @return 成功返回true, 失败返回false
	 */
	public boolean addTeacher(String name, String id, String gender, String work) {
		if (this.getResource(name) != null) {
			return false;
		}
		this.resources.add(Teacher.getNewTeacher(name, id, gender, work));
		checkRep();
		return true;
	}
	
	/**
	 * 增加一节课
	 * @param planname 课程名称
	 * @param locationname 教室名字
	 * @param begintime 开始时间
	 * @param endtime 结束时间
	 * @return 成功返回true, 失败返回false
	 */
	public boolean addPlan(String planname, String locationname, String begintime, String endtime) {
		if (this.getLocation(locationname) == null) {//位置未加入
			return false;
		}
		if(this.getPlan(planname) != null) {//课程已有
			return false;
		}
		if (Time.isLegalTime(begintime) == false || Time.isLegalTime(endtime) == false) {//时间输入格式不正确
			return false;
		}
		
		PlanningEntry<Teacher> plan = PlanningEntry.getNewPlanningEntry(planname);
		Timeslot timeslot = Timeslot.getNewTimeslot(begintime, endtime);
		Location location = Location.getNewLocation(locationname);
		plan.addLocation(location);
		plan.addTimeslot(timeslot);
		
		
		boolean flag = plans.add(plan);
		checkRep();
		return flag;
	}
	
	
	public boolean changeclassroom(String planname, String locationname) {
		if(this.getPlan(planname) == null || this.getLocation(locationname) == null) {
			return false;
		}
		String oldname = this.getPlan(planname).getLocations().get(0).getName();
		PlanningEntry<Teacher> course = this.getPlan(planname);
		if(course.getState().getName().equals("RUNNING")){
			return false;
		}
		if(course.getState().getName().equals("ENDED")) {
			return false;
		}
		if(course.getState().getName().equals("CANCELLED")) {
			return false;
		}
		course.removeLocation(oldname);
		course.addLocation(Location.getNewLocation(locationname));
		this.removePlan(planname);
		this.plans.add(course);
		return true;
	}
	
	@Override
	public void showBoard(String time, String roomName) {
		if (Time.isLegalTime(time) == false) {
			return;
		}
		CourseBoard.Board(Time.getNewTime(time), this.getLocation(roomName), this.plans);
	}
}

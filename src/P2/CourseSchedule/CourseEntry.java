package P2.CourseSchedule;

import P1.Location;
import P1.PlanningEntry;
import P1.PlanningEntryCollection;
import P1.Time;
import P1.Timeslot;

public class CourseEntry extends PlanningEntryCollection<Teacher>{
	// Abstraction function:
	// ��ʾ���ڿΣ�������ʦ�����ҡ��Ͽ�ʱ��
	
	// Representation invariant:
	// һ�ڿ�ֻ��һ��ʱ��κ�һ������
	
	// Safety from rep exposure:
	// �������Ϊprivate final
	// ʹ�÷����Կ���
	
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
	 * ����һ����ʦ
	 * @param name ��ʦ����
	 * @param id ���֤��
	 * @param gender �Ա�
	 * @param work ְ��
	 * @return �ɹ�����true, ʧ�ܷ���false
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
	 * ����һ�ڿ�
	 * @param planname �γ�����
	 * @param locationname ��������
	 * @param begintime ��ʼʱ��
	 * @param endtime ����ʱ��
	 * @return �ɹ�����true, ʧ�ܷ���false
	 */
	public boolean addPlan(String planname, String locationname, String begintime, String endtime) {
		if (this.getLocation(locationname) == null) {//λ��δ����
			return false;
		}
		if(this.getPlan(planname) != null) {//�γ�����
			return false;
		}
		if (Time.isLegalTime(begintime) == false || Time.isLegalTime(endtime) == false) {//ʱ�������ʽ����ȷ
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

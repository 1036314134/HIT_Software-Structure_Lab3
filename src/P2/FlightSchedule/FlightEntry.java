package P2.FlightSchedule;

import P1.Location;
import P1.PlanningEntry;
import P1.PlanningEntryCollection;
import P1.Time;
import P1.Timeslot;


public class FlightEntry extends PlanningEntryCollection<Plane> {
	// Abstraction function:
	// ��ʾ�������࣬�����ɻ�����ɽ���ʱ�䡢��ɽ������
	
	// Representation invariant:
	// һ������ֻ��һ�ܷɻ�������������һ��ʱ���
	
	// Safety from rep exposure:
	// �������Ϊprivate final
	// ʹ�÷����Կ���
	
	//constructor
	private FlightEntry() {
		super();
		checkRep();
	}

	public static FlightEntry getFlightEntry() {
		return new FlightEntry();
	}
	
	//methods
	/**
	 * ����һ�ܷɻ�
	 * @param planename �ɻ�����
	 * @param type ����
	 * @param seats ��λ��
	 * @param age ����
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean addPlane(String planename, String type, int seats, double age) {
		if (this.getResource(planename) != null) {//�ɻ�����
			return false;
		}

		this.resources.add(Plane.getNewPlane(planename, type, seats, age));
		checkRep();
		return true;
	}
	
	/**
	 * ���һ���ƻ�
	 * @param planname �ƻ�����
	 * @param from ʼ���ص�
	 * @param to ����ص�
	 * @param begintime ��ʼʱ��
	 * @param endtime ����ʱ��
	 * @return �ɹ�����true, ʧ�ܷ���false
	 */
	public boolean addPlan(String planname, String from, String to, String begintime, String endtime) {
		if (this.getLocation(from) == null || this.getLocation(to) == null) {//λ��δ����
			return false;
		}
		if (this.getPlan(planname) != null) {//��������
			return false;
		}
		if (Time.isLegalTime(begintime) == false || Time.isLegalTime(endtime) == false) {//ʱ�������ʽ����ȷ
			return false;
		}
		
		Timeslot timeslot = Timeslot.getNewTimeslot(begintime, endtime);
		PlanningEntry<Plane> line = PlanningEntry.getNewPlanningEntry(planname);
		
		line.addLocation(Location.getNewLocation(from));
		line.addLocation(Location.getNewLocation(to));
		line.addTimeslot(timeslot);
		this.plans.add(line);
		
		checkRep();
		return true;
	}

	
	@Override
	public void showBoard(String time, String locationName) {
		if (Time.isLegalTime(time) == false) {
			return;
		}
		FlightBoard.Board(Time.getNewTime(time), this.getLocation(locationName), this.plans);
	}

}

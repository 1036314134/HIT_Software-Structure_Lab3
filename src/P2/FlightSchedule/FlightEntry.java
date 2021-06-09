package P2.FlightSchedule;

import P1.Location;
import P1.PlanningEntry;
import P1.PlanningEntryCollection;
import P1.Time;
import P1.Timeslot;


public class FlightEntry extends PlanningEntryCollection<Plane> {
	// Abstraction function:
	// 表示数个航班，包含飞机、起飞降落时间、起飞降落机场
	
	// Representation invariant:
	// 一个航班只有一架飞机，两个机场和一个时间段
	
	// Safety from rep exposure:
	// 所有域均为private final
	// 使用防御性拷贝
	
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
	 * 增加一架飞机
	 * @param planename 飞机名字
	 * @param type 种类
	 * @param seats 座位数
	 * @param age 机龄
	 * @return 成功返回true，失败返回false
	 */
	public boolean addPlane(String planename, String type, int seats, double age) {
		if (this.getResource(planename) != null) {//飞机存在
			return false;
		}

		this.resources.add(Plane.getNewPlane(planename, type, seats, age));
		checkRep();
		return true;
	}
	
	/**
	 * 添加一个计划
	 * @param planname 计划名称
	 * @param from 始发地点
	 * @param to 到达地点
	 * @param begintime 开始时间
	 * @param endtime 结束时间
	 * @return 成功返回true, 失败返回false
	 */
	public boolean addPlan(String planname, String from, String to, String begintime, String endtime) {
		if (this.getLocation(from) == null || this.getLocation(to) == null) {//位置未加入
			return false;
		}
		if (this.getPlan(planname) != null) {//航班已有
			return false;
		}
		if (Time.isLegalTime(begintime) == false || Time.isLegalTime(endtime) == false) {//时间输入格式不正确
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

package P2.TrainSchedule;

import java.util.List;

import P1.Location;
import P1.PlanningEntry;
import P1.PlanningEntryCollection;
import P1.Time;
import P1.Timeslot;

public class TrainEntry extends PlanningEntryCollection<Train> {
	
	//constructor
	private TrainEntry() {
		super();
		checkRep();
	}

	public static TrainEntry getTrainEntry() {
		return new TrainEntry();
	}
	
	//methods
	/**
	 * 增加一辆货车
	 * @param name 火车名字
	 * @param type 种类
	 * @param seats 座位数
	 * @param year 生产年份
	 * @return 成功返回true, 失败返回false
	 */
	public boolean addTrain(String name, String type, int seats, int year) {
		if (this.getResource(name) != null) {//火车存在
			return false;
		}
		
		this.resources.add(Train.getNewTrain(name, type, seats, year));
		checkRep();
		return true;
	}
	
	/**
	 * 添加一个计划
	 * @param planname 计划名称
	 * @param locations 所有站点名称的集合
	 * @param times 所有时间的集合
	 * @return 成功返回true, 失败返回false
	 */
	public boolean addPlan(String planname, List<String> locations, List<String> times) {
		for(String l: locations) {//站点未加入
			if (this.getLocation(l) == null) {
				return false;
			}
		}
		if (this.getPlan(planname) != null) {//路线已有
			return false;
		}
		for(String t: times) {//时间输入格式不正确
			if (Time.isLegalTime(t) == false) {
				return false;
			}
		}
		if((locations.size()- 1) * 2  != times.size()) {//时间与站点数目不一致
			return false;
		}
		
		PlanningEntry<Train> line = PlanningEntry.getNewPlanningEntry(planname);
		int max = locations.size();
		for(int i = 0; i < max; i++) {
			line.addLocation(Location.getNewLocation(locations.get(i)));
		}
		for(int i = 0; i < 2*(max-1); i+= 2) {
			Timeslot timeslot = Timeslot.getNewTimeslot(times.get(i), times.get(i+1));
			line.addTimeslot(timeslot);
		}
		this.plans.add(line);
		checkRep();
		return true;
	}
	
	@Override
	public void showBoard(String time, String locationName) {
		if (Time.isLegalTime(time) == false) {
			return;
		}
		TrainBoard.Board(Time.getNewTime(time), this.getLocation(locationName), this.plans);
	}
}

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
	 * ����һ������
	 * @param name ������
	 * @param type ����
	 * @param seats ��λ��
	 * @param year �������
	 * @return �ɹ�����true, ʧ�ܷ���false
	 */
	public boolean addTrain(String name, String type, int seats, int year) {
		if (this.getResource(name) != null) {//�𳵴���
			return false;
		}
		
		this.resources.add(Train.getNewTrain(name, type, seats, year));
		checkRep();
		return true;
	}
	
	/**
	 * ���һ���ƻ�
	 * @param planname �ƻ�����
	 * @param locations ����վ�����Ƶļ���
	 * @param times ����ʱ��ļ���
	 * @return �ɹ�����true, ʧ�ܷ���false
	 */
	public boolean addPlan(String planname, List<String> locations, List<String> times) {
		for(String l: locations) {//վ��δ����
			if (this.getLocation(l) == null) {
				return false;
			}
		}
		if (this.getPlan(planname) != null) {//·������
			return false;
		}
		for(String t: times) {//ʱ�������ʽ����ȷ
			if (Time.isLegalTime(t) == false) {
				return false;
			}
		}
		if((locations.size()- 1) * 2  != times.size()) {//ʱ����վ����Ŀ��һ��
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

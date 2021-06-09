package P1;

import java.util.List;

public class PlanningEntryAPIsstrategy1 extends PlanningEntryAPIs{
	public static <R extends Resource> boolean checkLocationConflict(List<PlanningEntry<R>> entries) {
		if(entries.get(0).getLocations().size() > 1) {//大于一个位置说明是高铁或飞机，永不冲突
			return false;
		}
		
		for (PlanningEntry<R> p : entries) {
			for (PlanningEntry<R> q : entries) {
				if (p.getName().equals(q.getName())) {
					continue;
				}
				if(p.getLocations().get(0).getName().equals(q.getLocations().get(0).getName()) == false){
					continue;
				}
				if(p.getTimeslots().get(0).isConflict(q.getTimeslots().get(0))) {
					return true;
				}
			}
		}
			
		return false;
	}
}

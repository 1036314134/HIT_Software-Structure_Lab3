package P1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import P1.PlanningEntry;
import P1.Resource;

public class PlanningEntryAPIs{
	/**
	 * 检测一组计划项之间是否存在位置独占冲突
	 * @param entries 一组计划项
	 * @return 有冲突返回true, 没有返回false
	 */
	public static <R extends Resource> boolean checkLocationConflict(List<PlanningEntry<R>> entries, boolean f) {
		if(f) {
			return PlanningEntryAPIsstrategy1.checkLocationConflict(entries);
		}else{
			return PlanningEntryAPIsstrategy2.checkLocationConflict(entries);
		}
	}
	
	/**
	 * 检测一组计划项之间是否存在资源独占冲突
	 * @param entries
	 * @return
	 */
	public static <R extends Resource> boolean checkResourceExclusiveConflict(List<PlanningEntry<R>> entries) {
		for (PlanningEntry<R> p : entries) {
			for (PlanningEntry<R> q : entries) {
				if (p.getName().equals(q.getName())) {
					continue;
				}
				boolean flag = false;

				for (R i: p.getResources()) {
					for (R j: q.getResources()) {
						if (i.equals(j)) {
							flag = true;
							break;
						}
					}
				}
				
				if (flag == false) {
					continue;
				}
				
				for (Timeslot s : p.getTimeslots()) {
					for (Timeslot t : q.getTimeslots()) {
						if (s.isConflict(t)) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}
	
	/**
	 * 提取面向特定资源的前序计划项
	 * @param r 资源
	 * @param e 使用资源r的计划项
	 * @param entries 一组计划项
	 * @return 前序计划项
	 */
	public static <R extends Resource> PlanningEntry<R> findPreEntryPerResource(R r, PlanningEntry<R> e, List<PlanningEntry<R>> entries) {
		if (r == null || e == null) {
			return null;
		}

		if (e.getResources().contains(r) == false) {
			return null;
		}
		
		List<PlanningEntry<R>> plans = new ArrayList<>();

		for (PlanningEntry<R> f : entries) {
			if(e.getBegintime().compareto(f.getEndtime()) <= 0) {
				continue;
			}
			for (R i: f.getResources()) {
				if (i.getName().equals(r.getName())) {
					plans.add(f);
					break;
				}
			}
		}

		Collections.sort(plans, new Comparator<PlanningEntry<R>>() {
			@Override
			public int compare(PlanningEntry<R> a, PlanningEntry<R> b) {
				return a.getEndtime().compareto(b.getEndtime());
			}

		});

		if (plans.isEmpty()) {
			return null;
		}
		return plans.get(plans.size() - 1);
	}
	
}

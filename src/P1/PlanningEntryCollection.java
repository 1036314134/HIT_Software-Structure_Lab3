package P1;

import java.util.ArrayList;
import java.util.List;



public abstract class PlanningEntryCollection<R extends Resource> {
	protected final List<R> resources;
	protected final List<Location> locations;
	protected final List<PlanningEntry<R>> plans;
	
	protected void checkRep() {
		for (PlanningEntry<R> i : this.plans) {
			for (R j: i.getResources()) {
				assert resources.contains(j);
			}
		}
	}
	
	//constructor
	protected PlanningEntryCollection() {
		resources = new ArrayList<>();
		locations = new ArrayList<>();
		plans = new ArrayList<>();
	}
	
	//methods
	/**
	 * ����ĳ���ƻ�
	 * @param planname �ƻ�����
	 * @return �ɹ�����true�����򷵻�false
	 */
	public boolean start(String planname) {
		return this.getPlan(planname).start();
	}

	/**
	 * ȡ��ĳ���ƻ�
	 * @param planname �ƻ�����
	 * @return �ɹ�����true�����򷵻�false
	 */
	public boolean cancel(String planname) {
		return this.getPlan(planname).cancel();
	}
	
	/**
	 * ����ĳ���ƻ�
	 * @param planname
	 * @return �ɹ�����true�����򷵻�false
	 */
	public boolean block(String planname) {
		return this.getPlan(planname).block();
	}
	
	/**
	 * ���ĳ���ƻ�
	 * @param planname �ƻ�����
	 * @return �ɹ�����true�����򷵻�false
	 */
	public boolean complete(String planname) {
		return this.getPlan(planname).complete();
	}
	
	/**
	 * ��ȡĳ���ƻ���״̬
	 * @param planname �ƻ�����
	 * @return �ƻ�״̬
	 */
	public State getState(String planname) {
		return this.getPlan(planname).getState();
	}
	
	/**
	 * ��ȡĳ����Դ����Ϣ
	 * @param name ��Դ������
	 * @return ��Դ��Ϣ
	 */
	public R getResource(String name) {
		for (R i: this.resources) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * ɾ����Դ
	 * @param name ��Դ������
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean removeResource(String name) {
		if (this.getResource(name) == null) {
			return false;
		}
		this.resources.remove(this.getResource(name));
		checkRep();
		return true;
	}
	
	/**
	 * ������Դ���ƻ���
	 * @param resourceName ��Դ����
	 * @param planName �ƻ�����
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean allocateResource(String resourcename, String planname) {
		R resource = this.getResource(resourcename);
		PlanningEntry<R> plan = this.getPlan(planname);
		
		if (resource == null || plan == null) {
			return false;
		}
		
		this.getPlan(planname).addResource(resource);
		checkRep();
		return true;
	}
	
	/**
	 * ����һ����ַ
	 * @param name ��ַ����
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean addLocation(String name) {
		if(name == null) {
			return false;
		}
		if(this.getLocation(name) != null) {
			return false;
		}
		
		this.locations.add(Location.getNewLocation(name));
		checkRep();
		return true;
	}
	
	/**
	 * ��ȡ��ַ��Ϣ
	 * @param name ��ַ����
	 * @return ��ַ��Ϣ
	 */
	public Location getLocation(String name) {
		if (name == null) {
			return null;
		}
		for (Location i : this.locations) {
			if (i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * ɾ��һ����ַ
	 * @param name ��ַ����
	 * @return �ɹ�����true��ʧ�ܷ���false
	 */
	public boolean removeLocation(String name) {
		if(name == null) {
			return false;
		}
		if (this.getLocation(name) == null) {
			return false;
		}
		this.locations.remove(this.getLocation(name));
		checkRep();
		return true;
	}
	
	/**
	 * ��ȡĳ���ƻ�
	 * @param planname �ƻ�����
	 * @return �ƻ���Ϣ
	 */
	public PlanningEntry<R> getPlan(String planname){
		for(PlanningEntry<R> i: this.plans) {
			if(i.getName().equals(planname)) {
				return i;
			}
		}
		return null;
	}
	
	/**
	 * ɾ��ĳ���ƻ�
	 * @param planname �ƻ�����
	 * @return �ɹ�����true�����򷵻�false
	 */
	public boolean removePlan(String planname) {
		PlanningEntry<R> plan = this.getPlan(planname);
		if (plan == null) {
			return false;
		}
		if (plan.getState().getName().equals("WAITING") || this.getState(planname).getName().equals("ALLOCATED")) {
			this.plans.remove(plan);
			checkRep();
			return true;
		}
		return false;
	}
	
	/**
	 * ��ʾ��Ϣ��
	 * @param time ʱ��
	 * @param location �ص�
	 */
	abstract public void showBoard(String time, String location);
	
	/**
	 * ����ַ��ͻ
	 * @return ��ͻ����true, ���򷵻�false
	 */
	public boolean checkLocationConflict(boolean f) {
		return PlanningEntryAPIs.checkLocationConflict(plans, f);
	}
	
	/**
	 * �����Դ��ͻ
	 * @return ��ͻ����true, ���򷵻�false
	 */
	public boolean checkResourceConflict() {
		return PlanningEntryAPIs.checkResourceExclusiveConflict(plans);
	}


	/**
	 * �ҵ�ʹ��ĳ��Դ�����мƻ�
	 * @param resourceName ��Դ��
	 * @return ʹ�ø���Դ�����мƻ�
	 */
	public List<PlanningEntry<R>> getResourceEntries(String resourceName) {
		List<PlanningEntry<R>> ans = new ArrayList<>();

		if (resourceName == null || plans == null) {
			return ans;
		}

		for (PlanningEntry<R> e: plans) {
			for (R r: e.getResources()) {
				if (r.getName().equals(resourceName) && ans.contains(e) == false) {
					ans.add(e);
					break;
				}
			}
		}
		return ans;
	}
	
	/**
	 * ��ȡǰ��ƻ���
	 * @param resourceName ��Դ��
	 * @param entryName �ƻ���
	 * @return ǰ��ƻ���
	 */
	public PlanningEntry<R> getPreEntry(String resourceName, String entryName) {
		return PlanningEntryAPIs.findPreEntryPerResource(this.getResource(resourceName), this.getPlan(entryName), plans);
	}
	
	/**
	 * �����Դ�Ƿ�ָ��
	 * @param resourcename ��Դ����
	 * @return ��ָ�ɷ���true, ���򷵻�false
	 */
	public boolean checkResourceUsing(String resourcename) {
		for(PlanningEntry<R> e: this.plans) {
			for(R r: e.getResources()) {
				if(r.getName().equals(resourcename)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * ����ַ�Ƿ�ָ��
	 * @param locationname ��ַ����
	 * @return ��ָ�ɷ���true, ���򷵻�false
	 */
	public boolean checkLocationUsing(String locationname) {
		for(PlanningEntry<R> e: this.plans) {
			for(Location l: e.getLocations()) {
				if(l.getName().equals(locationname)) {
					return true;
				}
			}
		}
		return false;
	}
	
}

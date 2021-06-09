package P1;

import java.util.List;

public interface PlanningEntry<R> {
	
	//constructor
	public static <R> PlanningEntry<R> getNewPlanningEntry(String name) {
		return CommonPlanningEntry.getNewCommonPlanningEntry(name);
	}
	
	/**
	 * 获取计划的名字
	 * @return 计划名字
	 */
	public String getName();
	
	
	 /**
     * 启动计划
     * @return 计划成功启动返回true
     * 			否则返回false
     */
	public boolean start();
	
	
	/**
     * 取消计划
     * @return 计划成功取消返回true
     * 			否则返回false
     */
	public boolean cancel();
	
	
	/**
	 * 阻塞计划
	 * @return 计划成功阻塞返回true
     * 			否则返回false
	 */
	public boolean block();
	
	
	/**
     * 完成计划
     * @return 计划成功完成返回true
     * 			否则返回false
     */
	public boolean complete();


	/**
	 * 获取计划的状态
	 * @return 计划的状态
	 */
	public State getState();

	
	/**
	 * 增加资源
	 * @param resource 要加入的资源
	 * @return 成功增加返回true，否则返回false
	 */
	public boolean addResource(R resource);
	
	
	/**
	 * 获取所有资源
	 * @return 所有资源的集合
	 */
	public List<R> getResources();
	
	
	/**
	 * 添加地址
	 * @param location 地址
	 * @return 成功添加返回true，否则为false
	 */
	public boolean addLocation(Location location);
	
	
	/**
	 * 获取某个地址
	 * 
	 * @param locationname 地址名字
	 * @return 地址
	 */
	public Location getLocation(String locationname);
	
	/**
	 * 删除地址
	 * @param locationname 地址名字
	 * @return 成功返回true, 否则返回false
	 */
	public boolean removeLocation(String locationname);
	
	/**
	 * 获取所有地址
	 * @return 所有地址的集合
	 */
	public List<Location> getLocations();
	
	/**
	 * 添加时间段
	 * @param timeslot 时间段
	 * @return 成功添加返回true，否则为false
	 */
	public boolean addTimeslot(Timeslot timeslot);
	
	/**
	 * 获取时间段
	 * @return 时间段的集合
	 */
	public List<Timeslot> getTimeslots();
	
	/**
	 * 获取计划开始时间
	 * @return 开始时间
	 */
	public Time getBegintime();
	
	/**
	 * 获取计划结束时间
	 * @return 结束时间
	 */ 
	public Time getEndtime();
	
	/**
	 * 比较两个计划的早晚
	 * @param o 另一个计划
	 * @return 早返回-1, 晚返回1, 相同返回0
	 */
	public int compareTo(CommonPlanningEntry<R> o);
}

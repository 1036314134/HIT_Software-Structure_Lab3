package P2.TrainSchedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import P1.PlanningEntry;

public class TrainScheduleAPP {
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("高铁车次管理APP");
		TrainScheduleAPP.print();
		in.close();
	}
	
	public static void print() throws IOException {
		TrainEntry lines = TrainEntry.getTrainEntry();
		Scanner in = new Scanner(System.in);
		
		System.out.println("请选择你要执行的操作");
		System.out.println("1.增加一个车厢");
		System.out.println("2.移除一个车厢");
		System.out.println("3.新增一个站点");
		System.out.println("4.移除一个站点");
		System.out.println("5.增加一条路线");
		System.out.println("6.取消某条路线");
		System.out.println("7.让某组车厢去某条路线");
		System.out.println("8.启动某条路线");
		System.out.println("9.阻塞某条路线");
		System.out.println("10.结束某条路线");
		System.out.println("11.查看某条路线的状态");
		System.out.println("12.查看冲突");
		System.out.println("13.查看某节车厢的路线");
		System.out.println("14.显示计划版");
		System.out.println("15.离开");
		
		boolean flag = true;
		
		while(flag) {
			switch(in.nextInt()) {
			case 1:
				System.out.println("请输入加入车厢的信息");
				System.out.println("(名字 种类 座位数 生产年份)");
				
				if (lines.addTrain(in.next(), in.next(), in.nextInt(), in.nextInt())) {
					System.out.println("添加成功");
				}else {
					System.out.println("失败");
				}
				break;
			case 2:
				System.out.println("请输入要删除车厢的名字");
				String name = in.next();
				if(lines.checkResourceUsing(name)) {
					System.out.println("车厢已被指派");
					break;
				}
				if (lines.removeResource(name)) {
					System.out.println("添加成功");
				}else {
					System.out.println("失败");
				}
				break;
			case 3:
				System.out.println("请输入要增加的站点的名字");
				if (lines.addLocation(in.next())) {
					System.out.println("添加成功");
				}else {
					System.out.println("失败");
				}
					
				break;
			case 4:
				System.out.println("请给出要移除的站点的名字");
				String locationname = in.next();
				if(lines.checkLocationUsing(locationname)) {
					System.out.println("资源已被指派");
					break;
				}
				if (lines.removeLocation(locationname)) {
					System.out.println("删除成功");
				}else {
					System.out.println("失败");
				}
				break;
			case 5:
				System.out.println("请给出要增加的路线的信息");
				System.out.println("该线路名称：");
				String linename = in.next();
				System.out.println("该路线一共有几站？（包括起点站和终点站）");
				int max = in.nextInt();
				List<String> locations = new ArrayList<>();
				List<String> times = new ArrayList<>();
				System.out.println("请依次输入每个站的名字");
				for(int i = 0; i < max; i++) {
					locations.add(in.next());
				}
				System.out.println("请依次输入每两个站点之间时间段");
				for(int i = 0; i < (max-1)*2; i++) {
					times.add(in.next() + " " + in.next());
				}
				if (lines.addPlan(linename, locations, times)) {
					System.out.println("增加成功");
				}else {
					System.out.println("失败");
				}
				break;
			case 6:
				System.out.println("请给出要取消的路线名称");
				if (lines.cancel(in.next())) {
					System.out.println("取消成功");
				}else {
					System.out.println("失败");
				}
				break;
			case 7:
				System.out.println("请给出车厢名称和路线名称");
				if (lines.allocateResource(in.next(), in.next())) {
					System.out.println("成功");
				}else {
					System.out.println("失败");
				}
				break;
			case 8:
				System.out.println("请给出要启动的路线名称");
				if (lines.start(in.next())) {
					System.out.println("取消成功");
				}else {
					System.out.println("失败");
				}
				break;
			case 9:
				System.out.println("请给出要阻塞的路线名称");
				if (lines.block(in.next())) {
					System.out.println("取消成功");
				}else {
					System.out.println("失败");
				}
				break;
			case 10:
				System.out.println("请给出要结束的路线名称");
				if (lines.complete(in.next())) {
					System.out.println("取消成功");
				}else {
					System.out.println("失败");
				}
				break;
			case 11:
				System.out.println("请给出要查询的路线名称");
				PlanningEntry<Train> plan = lines.getPlan(in.next());
				if (plan == null) {
					System.out.println("未查询到该路线");
				}else {
					System.out.println("该路线状态为:" + plan.getState().getName());
				}
				break;
			case 12:
				boolean f = false;
				System.out.println("请选择检查车厢冲突的策略,1或者2");
				int k = in.nextInt();
				if(k == 1) {
					if(lines.checkLocationConflict(true)) {
						System.out.println("车厢有冲突");
						f = true;
					}
				}else if(k == 2) {
					if(lines.checkLocationConflict(false)) {
						System.out.println("车厢有冲突");
						f = true;
					}
				}else {
					System.out.println("输入错误");
					break;
				}
				if(lines.checkResourceConflict()) {
					System.out.println("机场有冲突");
					f = true;
				}
				if(!f) {
					System.out.println("没有冲突");
				}
				break;
			case 13:
				System.out.println("请输入车厢名字");
				String trainname = in.next();
				System.out.println(lines.getResourceEntries(trainname).toString());
				
				System.out.println("请选择其中一个班次");
				String coursename2 = in.next();
				if(lines.getPreEntry(trainname, coursename2) == null) {
					System.out.println("没有前序班次");
				}else {
					System.out.println("前序班次为：" + lines.getPreEntry(trainname, coursename2).toString());
				}
				break;
			case 14:
				System.out.println("请给出当前时间和站点");
				lines.showBoard(in.next() + " " + in.next(), in.next());
				break;
			case 15:
				flag = false;
				break;
			default:
				System.out.println("输入错误，请重新输入");
			}
		}
		
		
		
		
		
		
		
		
		
		
		in.close();
	}

}

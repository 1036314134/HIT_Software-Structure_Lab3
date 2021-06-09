package P2.FlightSchedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import P1.PlanningEntry;

public class FlightScheduleAPP {
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("航班管理APP");
		FlightScheduleAPP.print();
		in.close();
	}
	
	
	public static void print() throws IOException {
		FlightEntry lines = FlightEntry.getFlightEntry();
		Scanner in = new Scanner(System.in);

		System.out.println("请给出你要读取的文件的名字");
		System.out.println("例如：FlightSchedule_1.txt、FlightSchedule_2.txt、FlightSchedule_3.txt、FlightSchedule_4.txt、FlightSchedule_5.txt");

		while ((lines = FlightReader.fileReader(in.next())) == null) {
			System.out.println("输入的文件内有错误信息,请重新输入");
		}

		System.out.println("请选择你要执行的操作");
		System.out.println("0.增加一个有中转站的航程");
		System.out.println("1.增加一架飞机");
		System.out.println("2.移除一架飞机");
		System.out.println("3.新增一个机场");
		System.out.println("4.移除一个机场");
		System.out.println("5.增加一次航程");
		System.out.println("6.取消某个航程");
		System.out.println("7.让某个飞机去飞某个航程");
		System.out.println("8.启动某个航程");
		System.out.println("9.结束某个航程");
		System.out.println("10.查看某个航程的状态");
		System.out.println("11.查看冲突");
		System.out.println("12.查看某个飞机的航程");
		System.out.println("13.显示计划版");
		System.out.println("14.离开");

		boolean flag = true;

		while (flag) {
			switch (in.nextInt()) {
				case 0:
					System.out.println("请给出要增加的航班的信息");
					System.out.println("该航班名称：");
					String linename = in.next();
					List<String> locations = new ArrayList<>();
					List<String> times = new ArrayList<>();
					System.out.println("请依次输入每个机场的名字");
					for(int i = 0; i < 3; i++) {
						locations.add(in.next());
					}
					System.out.println("请依次输入每两个机场之间时间段");
					for(int i = 0; i < 4; i++) {
						times.add(in.next() + " " + in.next());
					}
					if (lines.addPlan(linename, locations, times)) {
						System.out.println("增加成功");
					}else {
						System.out.println("失败");
					}
					break;
				case 1:
					System.out.println("请给出你要添加的飞机的信息");
					System.out.println("飞机名称 飞机种类 飞机座位数 飞机机龄");
					if (lines.addPlane(in.next(), in.next(), in.nextInt(), in.nextDouble())) {
						System.out.println("添加成功");
					}else {
						System.out.println("失败");
					}
					break;
				case 2:
					System.out.println("请给出要删除的飞机的名字");
					String name = in.next();
					if(lines.checkResourceUsing(name)) {
						System.out.println("飞机已被指派");
						break;
					}
					if (lines.removeResource(name)) {
						System.out.println("删除成功");
					}else {
						System.out.println("失败");
					}
					break;
				case 3:
					System.out.println("请给出要增加的机场的名字");
					if (lines.addLocation(in.next())) {
						System.out.println("增加成功");
					}else {
						System.out.println("失败");
					}
					break;
				case 4:
					System.out.println("请给出要移除的机场的名字");
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
					System.out.println("请给出要增加的航程的信息");
					System.out.println("(航程名称 始发机场 到达机场 出发时间 到达时间)");
					if (lines.addPlan(in.next(), in.next(), in.next(), in.next() + " " + in.next(),in.next() + " " + in.next())) {
						System.out.println("增加成功");
					}else {
						System.out.println("失败");
					}
					break;
				case 6:
					System.out.println("请给出要删除的航程名称");
					if (lines.cancel(in.next())) {
						System.out.println("删除成功");
					}else {
						System.out.println("失败");
					}
					break;
				case 7:
					System.out.println("请给出飞机名称和航程名称");
					if (lines.allocateResource(in.next(), in.next())) {
						System.out.println("成功");
					}else {
						System.out.println("失败");
					}
					break;
				case 8:
					System.out.println("请输入航班名");
					if(lines.getPlan(in.next()).start()) {
						System.out.println("启动成功");
					}else {
						System.out.println("失败");
					}
					break;
				case 9:
					System.out.println("请输入航班名");
					if(lines.getPlan(in.next()).complete()) {
						System.out.println("结束成功");
					}else {
						System.out.println("失败");
					}
					break;
				case 10:
					System.out.println("请给出要查询的航程名称");
					PlanningEntry<Plane> plan = lines.getPlan(in.next());
					if (plan == null) {
						System.out.println("未查询到该航程");
					}else {
						System.out.println("该航程状态为:" + plan.getState().getName());
					}
					break;
				case 11:
					boolean f = false;
					System.out.println("请选择检查飞机冲突的策略,1或者2");
					int k = in.nextInt();
					if(k == 1) {
						if(lines.checkLocationConflict(true)) {
							System.out.println("飞机有冲突");
							f = true;
						}
					}else if(k == 2) {
						if(lines.checkLocationConflict(false)) {
							System.out.println("飞机有冲突");
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
				case 12:
					System.out.println("请输入飞机名字");
					String planename = in.next();
					System.out.println(lines.getResourceEntries(planename).toString());
					
					System.out.println("请选择其中一个航班");
					String linename2 = in.next();
					if(lines.getPreEntry(planename, linename2) == null) {
						System.out.println("没有前序航班");
					}else {
						System.out.println("前序航班为：" + lines.getPreEntry(planename, linename2).toString());
					}
					break;
				case 13:
					System.out.println("请给出当前时间和机场");
					lines.showBoard(in.next() + " " + in.next(), in.next());
					break;
				case 14:
					flag = false;
					break;
				default:
					System.out.println("输入错误，重新输入");
			}
		}

		in.close();
	}
}

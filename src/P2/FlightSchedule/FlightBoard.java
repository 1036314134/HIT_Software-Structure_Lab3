package P2.FlightSchedule;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import P1.Location;
import P1.PlanningEntry;
import P1.Resource;
import P1.Time;

public class FlightBoard {
	private static JFrame container;
	private static JTable jTable;


	public static <R extends Resource> void Board(Time time, Location location,List<PlanningEntry<R>> plans) {
		List<PlanningEntry<R>> list = new ArrayList<>();
		Time earlytime = Time.getNewTime(time.toString());
		Time latetime = Time.getNewTime(time.toString());
		Time time1 = Time.getNewTime("0000-00-00 -1:00");
		Time time2 = Time.getNewTime("0000-00-00 1:00");
		earlytime.addTime(time1);
		latetime.addTime(time2);
		
		for (PlanningEntry<R> line: plans) {//抵达
			if(line.getResources().isEmpty()) {//航班未分配飞机
				continue;
			}
			int max = line.getLocations().size();
			if(line.getLocations().get(max-1).equals(location) == false) {
				continue;
			}
			
			if(line.getTimeslots().get(0).getEnd().compareto(earlytime) < 0 
					|| line.getTimeslots().get(0).getEnd().compareto(latetime) > 0) {
				continue;
			}
			
			list.add(line);
		}

		CreateJTable(time, location, list, true);
		
		list.clear();

		for (PlanningEntry<R> line: plans) {//出发
			if(line.getResources().isEmpty()) {//航班未分配飞机
				continue;
			}
			if(line.getLocations().get(0).equals(location) == false) {
				continue;
			}
			if(line.getTimeslots().get(0).getBegin().compareto(earlytime) < 0 
					|| line.getTimeslots().get(0).getBegin().compareto(latetime) > 0) {
				continue;
			}
			list.add(line);
		}

		CreateJTable(time, location, list, false);
	}

	

	private static <R extends Resource> void CreateJTable(Time time, Location location, List<PlanningEntry<R>> lines, boolean arrived) {
		String[] columnName = { "时间", "飞机", "路线", "状态" };

		Object[][] data = new Object[lines.size()][4];
		for (int i = 0; i < lines.size(); i++) {
			if (arrived) {
				data[i][0] = String.format("%02d", lines.get(i).getTimeslots().get(0).getEnd().getHour()) + ":"
						+ String.format("%02d", lines.get(i).getTimeslots().get(0).getEnd().getMinute());
			}else {
				data[i][0] = String.format("%02d", lines.get(i).getTimeslots().get(0).getBegin().getHour()) + ":"
						+ String.format("%02d", lines.get(i).getTimeslots().get(0).getBegin().getMinute());
			}
			
			data[i][1] = lines.get(i).getResources().get(0).getName();
			
			String linename = lines.get(i).getLocations().get(0).getName() + "-";
			
			int length = lines.get(i).getLocations().size();
			linename = linename + lines.get(i).getLocations().get(length-1).getName();
			
			data[i][2] = linename;
			
			if(lines.get(i).getState().getName().equals("CANCELLED")) {
				data[i][3] = "已取消";
				continue;
			}
			
			if(time.compareto(lines.get(i).getTimeslots().get(0).getBegin()) > 0) {
				lines.get(i).start();
			}
			
			if(time.compareto(lines.get(i).getTimeslots().get(0).getEnd()) > 0) {
				lines.get(i).complete();
			}
			
			if(lines.get(i).getState().getName().equals("ALLOCATED")) {
				data[i][3] = "即将起飞";
				continue;
			}
			
			if(lines.get(i).getState().getName().equals("RUNNING")) {
				data[i][3] = "已起飞";
				continue;
			}
			if(lines.get(i).getState().getName().equals("ENDED")) {
				data[i][3] = "已降落";
				continue;
			}
			
		}

		jTable = new JTable(data, columnName);
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable);

		if (arrived) {
			container = new JFrame(time.toString() + " " + location.getName() + " 抵达航班");
		}else {
			container = new JFrame(time.toString() + " " + location.getName() + " 出发航班");
		}
		
		container.setSize(800, 600);
		container.setLayout(new BorderLayout());
		container.add(jScrollPane, BorderLayout.CENTER);
		container.setVisible(true);
	}
	
}

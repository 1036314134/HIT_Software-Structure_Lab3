package P2.CourseSchedule;

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
import P1.Timeslot;

public class CourseBoard {

	private static JFrame container;
	private static JTable jTable;
	
	public static <R extends Resource> void Board(Time time, Location location, List<PlanningEntry<R>> courses) {

		List<PlanningEntry<R>> list = new ArrayList<>();

		for (PlanningEntry<R> course : courses) {
			for (Timeslot s : course.getTimeslots()) {
				if (s.getBegin().sameday(time) || s.getEnd().sameday(time)) {
					continue;
				}
			}
			if(course.getLocations().get(0).getName().equals(location.getName()) == false) {
				continue;
			}
			if(course.getResources().isEmpty()) {
				continue;
			}
			
			list.add(course);
		}
			

		CreateJTable(time, location, list);
	}

	private static <R extends Resource> void CreateJTable(Time time, Location location, List<PlanningEntry<R>> courses) {
		String[] columnName = { "时间", "科目", "老师", "状态" };

		Object[][] data = new Object[courses.size()][4];
		for (int i = 0; i < courses.size(); i++) {
			data[i][0] = String.format("%02d", courses.get(i).getTimeslots().get(0).getBegin().getHour()) + ":"
					+ String.format("%02d", courses.get(i).getTimeslots().get(0).getBegin().getMinute()) + "-"
					+ String.format("%02d", courses.get(i).getTimeslots().get(0).getEnd().getHour()) + ":"
					+ String.format("%02d", courses.get(i).getTimeslots().get(0).getEnd().getMinute());

			data[i][1] = courses.get(i).getName();
			
			data[i][2] = "";
			for(int j = 0; j < courses.get(i).getResources().size(); j++) {
				data[i][2] = data[i][2] + "  " + courses.get(i).getResources().get(j).getName();
			}

			
			
			if(courses.get(i).getState().getName().equals("CANCELLED")) {
				data[i][3] = "已取消";
			}
			
			if(time.compareto(courses.get(i).getTimeslots().get(0).getBegin()) > 0) {
				courses.get(i).start();
			}
			
			if(time.compareto(courses.get(i).getTimeslots().get(0).getEnd()) > 0) {
				courses.get(i).complete();
			}
			
			if(courses.get(i).getState().getName().equals("ALLOCATED")) {
				data[i][3] = "计划";
				continue;
			}
			if(courses.get(i).getState().getName().equals("RUNNING")) {
				data[i][3] = "正在上课";
				continue;
			}
			if(courses.get(i).getState().getName().equals("ENDED")) {
				data[i][3] = "已下课";
				continue;
			}
		}

		jTable = new JTable(data, columnName);
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable);

		container = new JFrame(time.toString() + " " + location.getName());
		container.setSize(800, 600);
		container.setLayout(new BorderLayout());
		container.add(jScrollPane, BorderLayout.CENTER);
		container.setVisible(true);
	}
}

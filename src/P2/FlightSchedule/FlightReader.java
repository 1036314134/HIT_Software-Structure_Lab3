package P2.FlightSchedule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import P1.Time;

public class FlightReader {
	public static FlightEntry fileReader(String path) throws IOException {
		File file = new File("txt\\" + path);
		List<String> information = new ArrayList<>();
		FlightEntry ans = FlightEntry.getFlightEntry();

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;

			while ((line = reader.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				information.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		for (int i = 0; i < information.size(); i += 13) {
			String l = information.get(i);
			if (Pattern.matches("^Flight:(.*?)", l) == false) {
				return null;
			}
			String line = Arrays.asList(l.split(":")).get(1);
			List<String> linetimeandname = Arrays.asList(line.split(","));
			
			if (ans.getPlan(line) != null) {
				return null;
			}
			
			//始发机场
			l = information.get(i + 2);
			if (Pattern.matches("DepartureAirport:(.*?)", l) == false) {
				return null;
			}
			String departure = Arrays.asList(l.split(":")).get(1);
			ans.addLocation(departure);

			//到达机场
			l = information.get(i + 3);
			if (Pattern.matches("ArrivalAirport:(.*?)", l) == false) {
				return null;
			}
			String arrive = Arrays.asList(l.split(":")).get(1);
			ans.addLocation(arrive);
			
			//出发时间
			l = information.get(i + 4);
			if (Pattern.matches(
					"DepatureTime:((\\d{4})-(([0][1-9])|(1[012]))-((0[1-9])|([12]\\d)|30) (([01]\\d)|(2[0-4])):(([0-5]\\d)|60))",
					l) == false) {
				return null;
			}
			String beginTime = Arrays.asList(l.split(":")).get(1) + ":" + Arrays.asList(l.split(":")).get(2);

			//到达时间
			l = information.get(i + 5);
			if (Pattern.matches(
					"ArrivalTime:((\\d{4})-(([0][1-9])|(1[012]))-((0[1-9])|([12]\\d)|30) (([01]\\d)|(2[0-4])):(([0-5]\\d)|60))",
					l) == false) {
				return null;
			}
			String endTime = Arrays.asList(l.split(":")).get(1) + ":" + Arrays.asList(l.split(":")).get(2);

			//飞机信息
			l = information.get(i + 6);
			if (Pattern.matches("Plane:(B|N)(\\d{4})", l) == false) {
				return null;
			}
			String plane = Arrays.asList(l.split(":")).get(1);
			
			l = information.get(i + 8);
			if (Pattern.matches("Type:([a-zA-Z0-9]+)", l) == false) {
				return null;
			}
			String type = Arrays.asList(l.split(":")).get(1);

			l = information.get(i + 9);
			if (Pattern.matches("Seats:(([5-9][0-9])|([1-4]\\d{2})|500)", l) == false) {
				return null;
			}
			String seats = Arrays.asList(l.split(":")).get(1);
			
			l = information.get(i + 10);
			if (Pattern.matches("Age:(30|30.0|([1-2]?[0-9]([.][0-9])?))", l) == false) {
				return null;
			}
			String age = Arrays.asList(l.split(":")).get(1);
			
			ans.addPlane(plane, type, Integer.parseInt(seats), Double.parseDouble(age));
			

			
			
			if(linetimeandname.get(0).equals(Arrays.asList(beginTime.split(" ")).get(0)) == false) {
				return null;
			}
			Time begin = Time.getNewTime(beginTime);
			Time end = Time.getNewTime(endTime);
			if(begin.compareto(end) > 0) {
				return null;
			}

			ans.addPlan(line, departure, arrive, beginTime, endTime);
			ans.allocateResource(plane, line);
			
		}

		return ans;
	}
}

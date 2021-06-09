package P2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import P1.PlanningEntry;
import P2.FlightSchedule.FlightEntry;
import P2.TrainSchedule.Train;
import P2.TrainSchedule.TrainEntry;

public class TrainEntryTest {
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	
	
	@Test
	public void testResource() {
		TrainEntry plans = TrainEntry.getTrainEntry();
		assertEquals(plans.getResource("A"), null);
		assertEquals(plans.addTrain("A", "A380", 100, 10), true);
		assertEquals(plans.addTrain("B", "B380", 200, 20), true);
		assertEquals(plans.addTrain("B", "B380", 200, 20), false);
		assertEquals(plans.getResource("A").getName(), "A");
		assertEquals(plans.removeResource("A"), true);
		assertEquals(plans.getResource("A"), null);
	}
	
	
	@Test
	public void testLocation() {
		FlightEntry plans = FlightEntry.getFlightEntry();
		String locationname1 = "nantong";
		String locationname2 = "wuhu";
		assertEquals(plans.getLocation(locationname1), null);
		assertEquals(plans.addLocation(locationname1), true);
		assertEquals(plans.addLocation(locationname1), false);
		assertEquals(plans.addLocation(locationname2), true);
		assertEquals(plans.getLocation(locationname1).getName(), locationname1);
		assertEquals(plans.getLocation(locationname2).getName(), locationname2);
		assertEquals(plans.removeLocation(locationname1), true);
		assertEquals(plans.getLocation(locationname1), null);
	}
	
	
	@Test
	public void testPlan() {
		TrainEntry plans = TrainEntry.getTrainEntry();
		String locationname1 = "nantong";
		String locationname2 = "wuhu";
		String locationname3 = "hangzhou";
		String time1 = "2020-01-01 17:00";
		String time2 = "2020-01-01 19:00";
		String time3 = "2020-01-01 20:00";
		String time4 = "2020-01-01 22:00";
		List<String> locations = new ArrayList<String>();
		locations.add(locationname1);
		locations.add(locationname2);
		locations.add(locationname3);
		List<String> times = new ArrayList<String>();
		times.add(time1);
		times.add(time2);
		times.add(time3);
		times.add(time4);
		assertEquals(plans.addPlan("路线1", locations, times), false);
		plans.addLocation(locationname1);
		plans.addLocation(locationname2);
		plans.addLocation(locationname3);
		assertEquals(plans.addPlan("路线1", locations, times), true);
		assertEquals(plans.addPlan("路线1", locations, times), false);
		assertEquals(plans.addTrain("A", "A380", 100, 10), true);
		
		PlanningEntry<Train> line1 = plans.getPlan("路线1");
		
		assertEquals(line1.getState().getName(), "WAITING");
		assertEquals(line1.getLocations().get(0).getName(), locationname1);
		assertEquals(line1.getLocations().get(1).getName(), locationname2);
		assertEquals(line1.getLocations().get(2).getName(), locationname3);
		assertEquals(line1.getTimeslots().get(0).getBegin().toString(), "2020-01-01 17:00");
		assertEquals(line1.getTimeslots().get(0).getEnd().toString(), "2020-01-01 19:00");
		assertEquals(line1.getTimeslots().get(1).getBegin().toString(), "2020-01-01 20:00");
		assertEquals(line1.getTimeslots().get(1).getEnd().toString(), "2020-01-01 22:00");
		 
		assertEquals(plans.removePlan("路线1"), true);
		assertEquals(plans.getPlan("路线1"), null);
		
	}
	
	@Test
	public void testState() {
		TrainEntry plans = TrainEntry.getTrainEntry();
		String locationname1 = "nantong";
		String locationname2 = "wuhu";
		String locationname3 = "hangzhou";
		String time1 = "2020-01-01 17:00";
		String time2 = "2020-01-01 19:00";
		String time3 = "2020-01-01 20:00";
		String time4 = "2020-01-01 22:00";
		List<String> locations = new ArrayList<String>();
		locations.add(locationname1);
		locations.add(locationname2);
		locations.add(locationname3);
		List<String> times = new ArrayList<String>();
		times.add(time1);
		times.add(time2);
		times.add(time3);
		times.add(time4);
		
		plans.addLocation(locationname1);
		plans.addLocation(locationname2);
		plans.addLocation(locationname3);
		assertEquals(plans.addPlan("路线1", locations, times), true);
		assertEquals(plans.addPlan("路线1", locations, times), false);
		assertEquals(plans.addTrain("A", "A380", 100, 10), true);
		
		assertEquals(plans.getPlan("路线1").getState().getName(), "WAITING");
		assertEquals(plans.start("路线1"), false);
		assertEquals(plans.allocateResource("A", "路线1"), true);
		assertEquals(plans.getPlan("路线1").getState().getName(), "ALLOCATED");
		assertEquals(plans.start("路线1"), true);
		assertEquals(plans.getPlan("路线1").getState().getName(), "RUNNING");
		assertEquals(plans.complete("路线1"), true);
		assertEquals(plans.getPlan("路线1").getState().getName(), "ENDED");
		
	
		
	}
	
	
	
	
	
	
	

}

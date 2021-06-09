package P2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import P1.PlanningEntry;
import P2.FlightSchedule.FlightEntry;
import P2.FlightSchedule.Plane;

public class FlightEntryTest {
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	
	
	@Test
	public void testResource() {
		FlightEntry plans = FlightEntry.getFlightEntry();
		assertEquals(plans.getResource("A"), null);
		assertEquals(plans.addPlane("A", "A380", 100, 10), true);
		assertEquals(plans.addPlane("B", "B380", 200, 20), true);
		assertEquals(plans.addPlane("B", "B380", 200, 20), false);
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
		FlightEntry plans = FlightEntry.getFlightEntry();
		String locationname1 = "nantong";
		String locationname2 = "wuhu";
		String begintime1 = "2020-01-01 17:00";
		String endtime1 = "2020-01-01 19:00";
		
		assertEquals(plans.addPlan("����1", locationname1, locationname2, begintime1, endtime1), false);
		
		assertEquals(plans.addLocation(locationname1), true);
		assertEquals(plans.addLocation(locationname2), true);
		assertEquals(plans.addPlane("A", "A380", 100, 10), true);
		
		assertEquals(plans.addPlan("����1", locationname1, locationname2, begintime1, endtime1), true);
		assertEquals(plans.addPlan("����1", locationname1, locationname2, begintime1, endtime1), false);
		
		PlanningEntry<Plane> line1 = plans.getPlan("����1");
		
		assertEquals(line1.getState().getName(), "WAITING");
		assertEquals(line1.getLocations().get(0).getName(), locationname1);
		assertEquals(line1.getLocations().get(1).getName(), locationname2);
		assertEquals(line1.getTimeslots().get(0).getBegin().toString(), "2020-01-01 17:00");
		assertEquals(line1.getTimeslots().get(0).getEnd().toString(), "2020-01-01 19:00");
		
		assertEquals(plans.removePlan("����1"), true);
		assertEquals(plans.getPlan("����1"), null);
	}
	
	
	@Test
	public void testState() {
		FlightEntry plans = FlightEntry.getFlightEntry();
		String locationname1 = "nantong";
		String locationname2 = "wuhu";
		String begintime1 = "2020-01-01 17:00";
		String endtime1 = "2020-01-01 19:00";
		
		assertEquals(plans.addLocation(locationname1), true);
		assertEquals(plans.addLocation(locationname2), true);
		assertEquals(plans.addPlane("A", "A380", 100, 10), true);
		assertEquals(plans.addPlan("����1", locationname1, locationname2, begintime1, endtime1), true);
		assertEquals(plans.addPlan("����2", locationname1, locationname2, begintime1, endtime1), true);
		
		assertEquals(plans.start("����1"), false);
		assertEquals(plans.complete("����1"), false);
		assertEquals(plans.allocateResource("A", "����1"), true);
		assertEquals(plans.getState("����1").getName(), "ALLOCATED");
		assertEquals(plans.start("����1"), true);
		assertEquals(plans.getState("����1").getName(), "RUNNING");
		assertEquals(plans.cancel("����1"), false);
		assertEquals(plans.complete("����1"), true);
		assertEquals(plans.getState("����1").getName(), "ENDED");
		
		assertEquals(plans.allocateResource("A", "����2"), true);
		assertEquals(plans.getState("����2").getName(), "ALLOCATED");
		assertEquals(plans.cancel("����2"), true);
		assertEquals(plans.getState("����2").getName(), "CANCELLED");
		
	}
}

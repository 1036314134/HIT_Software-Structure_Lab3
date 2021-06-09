package P1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlanningEntryTest {
	
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	
	@Test
	public void testgetName() {
		String name = "wuhuqifei";
		PlanningEntry<Resource> plan = PlanningEntry.getNewPlanningEntry(name);
		
		assertEquals(plan.getName(), name);
	}
	
	@Test
	public void teststart() {
		String name = "wuhuqifei";
		PlanningEntry<Resource> plan = PlanningEntry.getNewPlanningEntry(name);
		
		assertEquals(plan.start(), false);
	}
	
	@Test
	public void testcancel() {
		String name = "wuhuqifei";
		PlanningEntry<Resource> plan = PlanningEntry.getNewPlanningEntry(name);
		
		assertEquals(plan.cancel(), true);
	}
	
	@Test
	public void testcomplete() {
		String name = "wuhuqifei";
		PlanningEntry<Resource> plan = PlanningEntry.getNewPlanningEntry(name);
		
		assertEquals(plan.complete(), false);
	}
	
	@Test
	public void testgetState() {
		String name = "wuhuqifei";
		PlanningEntry<Resource> plan = PlanningEntry.getNewPlanningEntry(name);
		
		assertEquals(plan.getState().getName(), "WAITING");
	}
	
	@Test
	public void testaddResourceandgetResource() {
		String name = "wuhuqifei";
		PlanningEntry<Resource> plan = PlanningEntry.getNewPlanningEntry(name);
		
		assertEquals(plan.getResources().isEmpty(), true);
		assertEquals(plan.addResource(new Resource("feiji1")), true);
		assertEquals(plan.getResources().size(), 1);
		assertEquals(plan.addResource(new Resource("feiji2")), true);
		assertEquals(plan.getResources().size(), 2);
		assertEquals(plan.addResource(new Resource("feiji2")), false);
		assertEquals(plan.getResources().size(), 2);
	}
	
	@Test
	public void testaddLocationandgetLocation() {
		String name = "wuhuqifei";
		PlanningEntry<Resource> plan = PlanningEntry.getNewPlanningEntry(name);
		
		Time time1 = Time.getNewTime(2020, 3, 21, 10, 30);
		Time time2 = Time.getNewTime(2020, 3, 21, 11, 00);
		Time time3 = Time.getNewTime(2020, 3, 21, 11, 30);
		
		Timeslot timeslot1 = Timeslot.getNewTimeslot(time1, time2);
		Timeslot timeslot2 = Timeslot.getNewTimeslot(time2, time3);
		
		Location location1 = Location.getNewLocation("wuhu");
		Location location2 = Location.getNewLocation("nantong");
		
		assertEquals(plan.addLocation(location1), true);
		assertEquals(plan.addLocation(location2), true);
		assertEquals(plan.addLocation(location2), false);
		
		assertEquals(plan.addTimeslot(timeslot1), true);
		assertEquals(plan.addTimeslot(timeslot2), true);
		assertEquals(plan.addTimeslot(timeslot2), false);
	}
	
	
	
	
	
}

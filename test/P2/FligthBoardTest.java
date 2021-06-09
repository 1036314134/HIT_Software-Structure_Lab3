package P2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import P2.FlightSchedule.FlightEntry;

public class FligthBoardTest {
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	
	
	@Test
	public void testBorad() {
		FlightEntry plans = FlightEntry.getFlightEntry();
		String locationname1 = "harbin";
		String locationname2 = "nantong";
		String begintime1 = "2020-01-01 15:00";
		String endtime1 = "2020-01-01 18:00";
		String nowtime1 = "2020-01-01 14:30";
		assertEquals(plans.addLocation(locationname1), true);
		assertEquals(plans.addLocation(locationname2), true);
		assertEquals(plans.addPlane("A", "A380", 100, 10), true);
		assertEquals(plans.addPlan("º½°à1", locationname1, locationname2, begintime1, endtime1), true);
		assertEquals(plans.allocateResource("A", "º½°à1"), true);
		plans.showBoard(nowtime1 ,locationname1);
		
		
		
		
		
		
		
	}

}

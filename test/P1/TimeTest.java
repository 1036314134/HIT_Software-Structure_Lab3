package P1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimeTest {
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	
	@Test
	public void testaddTime() {
		Time time1 = Time.getNewTime("2020-01-01 18:00");
		Time time2 = Time.getNewTime("2020-01-01 19:00");
		
		assertEquals(time1.equals(time2), false);
		assertEquals(time1.compareto(time2), -1);
		
		Time time3 = Time.getNewTime("0000-00-00 1:00");
		Time time4 = Time.getNewTime("0000-00-00 -1:00");
		
		time1.addTime(time3);
		
		assertEquals(time1.toString().equals(time2.toString()), true);
		assertEquals(time1.compareto(time2), 0);
		Time time5 = Time.getNewTime("2020-01-01 19:00");
		time5.addTime(time4);
		assertEquals(time1.toString().equals(time2.toString()), true);
		assertEquals(time1.compareto(time2), 0);
		
		Time time6 = Time.getNewTime("2020-01-01 15:00");
		Time time7 = Time.getNewTime("2020-01-01 15:30");
		
		time7.addTime(time4);
		
		assertEquals(time6.compareto(time7), 1);
		
	}
}

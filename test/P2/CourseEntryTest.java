package P2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import P2.CourseSchedule.CourseEntry;

public class CourseEntryTest {
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	
	@Test
	public void testResource() {
		CourseEntry courses = CourseEntry.getCourseEntry();
		assertEquals(courses.getResource("A"), null);
		assertEquals(courses.addTeacher("A", "320602200003212516", "男", "教授"), true);
		assertEquals(courses.addTeacher("B", "320602200003212516", "女", "讲师"), true);
		assertEquals(courses.addTeacher("B", "320602200003212516", "女", "讲师"), false);
		assertEquals(courses.getResource("A").getName(), "A");
		assertEquals(courses.removeResource("A"), true);
		assertEquals(courses.getResource("A"), null);
	}
	
	@Test
	public void testLocation() {
		CourseEntry courses = CourseEntry.getCourseEntry();
		String begintime1 = "2020-01-01 17:00";
		String endtime1 = "2020-01-01 19:00";
		String locationname1 = "正心1";
		String locationname2 = "正心2";
		String coursename1 = "英语";
		
		courses.addTeacher("A", "101", "man", "teacher");
		courses.addLocation(locationname1);
		courses.addLocation(locationname2);
		
		courses.addPlan(coursename1, locationname1, begintime1, endtime1);
		assertEquals(courses.getPlan(coursename1).getLocations().get(0).getName(), locationname1);
		assertEquals(courses.getPlan(coursename1).getState().getName(), "WAITING");
		assertEquals(courses.changeclassroom(coursename1, locationname2), true);
		assertEquals(courses.getPlan(coursename1).getLocations().get(0).getName(), locationname2);
		
		
		
	}
	
	@Test
	public void testState() {
		CourseEntry courses = CourseEntry.getCourseEntry();
		String begintime1 = "2020-01-01 17:00";
		String endtime1 = "2020-01-01 19:00";
		
		courses.addTeacher("A", "101", "man", "teacher");
		courses.addLocation("正心1");
		courses.addPlan("英语", "正心1", begintime1, endtime1);
		assertEquals(courses.cancel("英语"), true);
		
		
	}
}

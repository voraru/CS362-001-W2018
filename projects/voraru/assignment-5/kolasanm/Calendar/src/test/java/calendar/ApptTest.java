package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		
	  	/**** Test time/day data get methods ***/

		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";
		 //Construct a new Appointment object with the initial data	 
		 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);

		 assertTrue(appt.getValid());
		 //assertTrue(appt.isRecurring()); // Checks to ensure that return value is true
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());

		 /*** Test recurrence get methods. ***/ 

		 // Call setRecurrence() function
		 int[] recurDays = {1, 3, 5};
		 int recurBy = 1;
		 int recurIncrement = 2;
		 int recurNumber = 3;

		 appt.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);

		 assertNotNull(appt.getRecurDays());
		 assertEquals(1, appt.getRecurBy());
		 assertEquals(2, appt.getRecurIncrement());
		 assertEquals(3, appt.getRecurNumber()); 

	 }

	 @Test
	  public void test02()  throws Throwable  {

	  	/*** Test that all of the set methods work as expected. ***/

		 int startHour=21;
		 int startMinute=30;
		 int startDay=15;
		 int startMonth=01;
		 int startYear=2018;
		 String title="Birthday Party";
		 String description="This is my birthday party.";

	  	 Appt appt = new Appt(startHour,
		          startMinute ,
		          startDay ,
		          startMonth ,
		          startYear ,
		          title,
		         description);
	  	 
	  	 // Sets new values
	  	 appt.setStartHour(12);
	  	 appt.setStartMinute(35);
	  	 appt.setStartDay(14);
	  	 appt.setStartMonth(03);
	  	 appt.setStartYear(2017);
	  	 appt.setTitle("New appt");
	  	 appt.setDescription("New description");
		 
		 // Checks to see if values are updated 
		 assertTrue(appt.getValid());
		 //assertTrue(appt.isRecurring()); // Checks to ensure that return value is true
	  	 assertEquals(12, appt.getStartHour());
		 assertEquals(35, appt.getStartMinute());
		 assertEquals(14, appt.getStartDay());
		 assertEquals(03, appt.getStartMonth());
		 assertEquals(2017, appt.getStartYear());
		 assertEquals("New appt", appt.getTitle());
		 assertEquals("New description", appt.getDescription());

		 // Check mutant boundary conditions
		 
		 appt.setStartMinute(0);
		 assertTrue(appt.getValid());

		 appt.setStartMinute(-59);
		 assertTrue(appt.getValid());

		 appt.setStartHour(0);
		 assertTrue(appt.getValid());

		 appt.setStartHour(23);
		 assertTrue(appt.getValid());


		 appt.setStartDay(1);
		 assertTrue(appt.getValid());

		 appt.setStartDay(31);
		 assertTrue(appt.getValid());
		 assertEquals(31, appt.getStartDay());

		 appt.setStartMonth(1);
		 assertTrue(appt.getValid());

		 appt.setStartMonth(12);
		 assertTrue(appt.getValid());

		 // Check mutant call removal
		 appt.setStartHour(80);
		 assertFalse(appt.getValid());

		 appt.setStartMinute(300);
		 assertFalse(appt.getValid());

		 /*appt.setStartMonth(80);
		 assertFalse(appt.getValid());*/

		 appt.setStartDay(200);
		 assertFalse(appt.getValid());

		 appt.setStartYear(12345);
		 assertFalse(appt.getValid());



		 // Mutations in compareTo()
		Appt appt1 = new Appt(12,
		          40 ,
		          01 ,
		          06 ,
		          2017 ,
		          "Hello world",
		         "New appt");

	  	 Appt appt2 = new Appt(12,
		          40 ,
		          01 ,
		          06 ,
		          2017 ,
		          "Hello world",
		         "New appt");


	  	 assertEquals(10861, appt.compareTo(appt2)); // Ensures that the correct value is returned
	  	 assertEquals(0, appt1.compareTo(appt2)); // Ensures that the correct value is returned


		 // Mutations in toString and representation app
		 appt.setStartHour(-1);
		 assertNull(appt.toString());

		 // Resets the values to proper values 
		 appt.setStartHour(1);
	  	 appt.setStartMinute(35);
	  	 appt.setStartDay(14);
	  	 appt.setStartMonth(03);
	  	 appt.setStartYear(2017);
	  	 appt.setTitle("NULL");
	  	 appt.setDescription("NULL");

		 assertNotNull(appt.toString());  // Checks to ensure that a string is returned
		 assertEquals("	3/14/2017 at 1:35am ,NULL, NULL\n", appt.toString()); // Checks to ensure that the string is valid


	 } 
}
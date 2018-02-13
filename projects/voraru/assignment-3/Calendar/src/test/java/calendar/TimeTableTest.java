package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	 @Test
	  public void test01()  throws Throwable  {

	  	TimeTable timeTable = new TimeTable();

	  	// Create first and last day for range
	  	GregorianCalendar firstDay = new GregorianCalendar(2017, 01, 01);
	  	GregorianCalendar lastDay = new GregorianCalendar(2017, 12, 31);

	  	// Create list of appointments
	  	LinkedList<Appt> appts = new LinkedList<Appt>();
	  	Appt appt1 = new Appt(20, 30, 12, 10, 2017, "Appt 1", "Test appointment 1");
	  	Appt appt2 = new Appt(21, 15, 11, 11, 2017, "Appt 2", "Test appointment 2");
	  	Appt appt3 = new Appt(22, 0, 10, 10, 2017, "Appt 3", "Test appointment 3");
	  	Appt appt4 = new Appt(22, 0, 10, 10, 2017, "Appt 4", "Test appointment 4");
	  	Appt appt5 = new Appt(22, 0, 10, 10, 2017, "Appt 5", "Test appointment 5");

	 	// Set up for testing of getNextApptOccurence() method from call to getApptRange()
	  	 int[] recurDays = {1, 3, 5};
		 int recurBy = 1;
		 int recurIncrement = 2;
		 int recurNumber = 3;

		 // Sets the recurrence info for the first appointment 
		 appt1.setRecurrence(recurDays, recurBy, recurIncrement, recurNumber);

	  	appts.add(appt1);
	  	appts.add(appt2); 
	  	appts.add(appt3); 
	  	appts.add(appt4);
	  	appts.add(appt5); // Adds the five apppointments

	  	// Test permute method
	  	int[] order = {1, 0, 2, 3, 4};
	  	assertNotNull(timeTable.permute(appts, order)); 

		// TEST getApptRange() METHOD
		LinkedList<Appt> appts1 = new LinkedList<Appt>();
		Appt badAppt1 = new Appt(400, 400, 45, 02, 12345, "Appt 1", "Test appointment 1");
	  	Appt badAppt2 = new Appt(21, 15, 29, 02, 2017, "Appt 2", "Test appointment 2");
	  	
	  	appts1.add(badAppt1);
	  	int[] badRecurDays = {1, 3, 29};
	  	badAppt1.setRecurrence(badRecurDays, recurBy, recurIncrement, recurNumber);
	  	appts1.add(badAppt2);

	  	assertNotNull(timeTable.getApptRange(appts, firstDay, lastDay));
	  	assertNotNull(timeTable.getApptRange(appts1, firstDay, lastDay));
	  	//assertNotNull(timeTable.getApptRange(appts, lastDay, firstDay));

	  	// Test delete appointment method
	  	assertNull(timeTable.deleteAppt(appts, appt5)); // Verify that the return value is not null as there should be one appointment remaining 
	  	//assertNull(timeTable.deleteAppt(appts, appt4)); // Verify that the return value is not null as there should be one appointment remaining 
	  	//assertNull(timeTable.deleteAppt(appts, appt3)); // Verify that the return value is not null as there should be one appointment remaining 
	  	//assertNull(timeTable.deleteAppt(appts, appt2)); // Verify that the return value is null
	  	//assertNull(timeTable.deleteAppt(appts, appt1)); // Verify that the return value is null 
	  	assertNull(timeTable.deleteAppt(null, null)); // Verify null return value

	  	// Test mutants in delete mutants
	  	assertNull(timeTable.deleteAppt(appts, null));
	  	assertNull(timeTable.deleteAppt(null, appt1));
	
		 
	 }
	 @Test
	  public void test02()  throws Throwable  {

	  	// Mutation testing
	  	TimeTable timeTable = new TimeTable();

	  	// Create first and last day for range
	  	GregorianCalendar firstDay = new GregorianCalendar(2017, 01, 01);
	  	GregorianCalendar lastDay = new GregorianCalendar(2017, 01, 02);

	  	// Create calendar days
	  	CalDay calDay1 = new CalDay(firstDay);
	  	CalDay calDay2 = new CalDay(lastDay);

	  	// Create list of appointments
	  	LinkedList<Appt> appts = new LinkedList<Appt>();
	  	Appt appt1 = new Appt(20, 30, 01, 01, 2017, "Appt 1", "Test appointment 1"); // Day 1 appts
	  	Appt appt2 = new Appt(21, 15, 01, 01, 2017, "Appt 2", "Test appointment 2");
	  	Appt appt3= new Appt(20, 30, 02, 01, 2017, "Appt 1", "Test appointment 1"); // Day 2 appts
	  	Appt appt4 = new Appt(21, 15, 02, 01, 2017, "Appt 2", "Test appointment 2");

	  	// Checking delete appt mutants
	  	assertNull(timeTable.deleteAppt(appts, appt1));
	    // Kill mutant if (!appt.getValid())
	  	appt4.setStartHour(24);
	  	assertFalse(appt4.getValid());
	  	assertNull(timeTable.deleteAppt(appts, appt4));

	  	appts.add(appt1);
	  	appts.add(appt2);
	  	appts.add(appt3);
	  	appts.add(appt4);	// Add the appts to the list 

	  	// Set recurrence for appointments  	
	  	int[] recurDays = {2, 4, 6};
	  	// Set recurrence to call private method
	  	appt1.setRecurrence(recurDays, 1, 2, 3);

	  	int[] recurringDays = new int[0];	
        appt2.setRecurrence(recurringDays, 2, 0, -1);

        int[] recurDays2 = {1};
        appt3.setRecurrence(recurDays, 1, 0, 1000);


	  	// Add appointments to each of the caldays
	  	calDay1.addAppt(appt1);
	  	calDay1.addAppt(appt2);
	  	calDay2.addAppt(appt3);
	  	calDay2.addAppt(appt4);

	  	assertNotNull(timeTable.getApptRange(appts, firstDay, lastDay));
	  	assertEquals(1, timeTable.getApptRange(appts, firstDay, lastDay).get(0).getSizeAppts()); // Makes sure the 2nd appointment isn't added
	  	assertNotNull(timeTable.getApptRange(appts, firstDay, lastDay).get(0).getAppts()); // Checks to ensure that appointment list occurs 
	  	assertEquals(1, timeTable.getApptRange(appts, firstDay, lastDay).size()); // Makes sure the size of the list is 1
		 
	 }
//add more unit tests as you needed
}

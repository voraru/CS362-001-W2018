package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	 @Test
	  public void test01()  throws Throwable  {

	  	// Create new calendar
	  	GregorianCalendar cal = new GregorianCalendar(2018, 11, 22);
	  	// Create calDay object
	  	CalDay calDay = new CalDay(cal);

	  // Tests all getter functions
	  	//assertNotNull(calDay.getAppts()); // Checks for valid pointer return type
	  	assertEquals(22, calDay.getDay());
	  	assertEquals(11, calDay.getMonth());
	  	assertEquals(2018, calDay.getYear());
	  	assertNotNull(calDay.getAppts());

	  // Tests the appointment size getter
	  	// Creates two new appointments so that appointment size is 2
	  	Appt appt1 = new Appt(20, 30, 12, 10, 2016, "Appt 1", "Test appointment 1");
	  	Appt appt2 = new Appt(21, 15, 11, 11, 2017, "Appt 2", "Test appointment 2");

	  	// Adds the two new appointments
	  	calDay.addAppt(appt1);
	  	calDay.addAppt(appt2);

	  	assertEquals(2, calDay.getSizeAppts()); // Verifies that the correct size is 2

	  	// Mutant testing
	  	Appt a1 = new Appt(21, 30, 12, 10, 2016, "Appt 1", "Test appointment 1");
	  	Appt a2 = new Appt(20, 15, 11, 11, 2017, "Appt 2", "Test appointment 2");
	  	Appt a3 = new Appt(20, 15, 11, 11, 2017, "appt 3", "null");

	  	calDay.addAppt(a1);
	  	calDay.addAppt(a2);
	  	calDay.addAppt(a3);

	  	// Killing a mutant that negated a boundary
	  	int check = 0;
	  	LinkedList<Appt> Apt = calDay.getAppts();
	  	if (Apt.get(0).getStartHour() > Apt.get(1).getStartHour()) {
	  		check = 1;
	  	}
	  	assertEquals(check, 0);

	  	assertEquals(5, calDay.getSizeAppts());


	 }
	 @Test
	  public void test02()  throws Throwable  {

	  	// Tests the toString() method
	  	GregorianCalendar cal = new GregorianCalendar(2018, 11, 22);
	  	CalDay calDay = new CalDay(cal);

	  	assertNotNull(calDay.toString());

	  	// Tests the iterator
	  	assertNotNull(calDay.iterator()); // Checks to ensure that the iterator is not null since 
	  									// valid should be set to true following the creation of a valid calendar day

	  	// Tests the null on the iterator
	  	CalDay calDay2 = new CalDay();
	  	calDay2.toString();
	  	assertNull(calDay2.iterator());

	  	assertNotNull(calDay2.toString());
		 
	 }
//add more unit tests as you needed	
}
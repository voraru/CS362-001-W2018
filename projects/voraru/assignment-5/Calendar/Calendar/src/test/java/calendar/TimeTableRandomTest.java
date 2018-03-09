package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;
import java.util.*;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {

	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	private static final int NUM_TESTS=100;

  	// Creates the timeTable object
  	TimeTable timeTable = new TimeTable();

  	// Create first and last day for range
  	GregorianCalendar firstDay = new GregorianCalendar(2017, 01, 01);
  	GregorianCalendar lastDay = new GregorianCalendar(2017, 12, 31);

  	// Create linked list 
  	LinkedList<Appt> appts = new LinkedList<Appt>();
	
    /**
     * Generate Random Tests that tests TimeTable Class.
     */

	 @Test
	  public void radnomtest()  throws Throwable  {

	  	long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		 
		 System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
	//			System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);
				

				 int startHour=ValuesGenerator.RandInt(random);
				 int startMinute=ValuesGenerator.RandInt(random);
				 int startDay=ValuesGenerator.RandInt(random);;
				 int startMonth=ValuesGenerator.getRandomIntBetween(random, 1, 11);
				 int startYear=ValuesGenerator.RandInt(random);
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
			 if(!appt.getValid())continue;

			 // Delete appt random coverage
			 appts.add(appt); // Adds appointment to the list 
			 assertNull(timeTable.deleteAppt(appts, appt)); // Deletes the appointment that got added 
			 assertNull(timeTable.deleteAppt(null, null)); // Verify null return value

			 assertNull(timeTable.deleteAppt(appts, null));
	  		 assertNull(timeTable.deleteAppt(null, appt));

			 // Check appt range random coverage
			 assertNotNull(timeTable.getApptRange(appts, firstDay, lastDay));
			 assertNotNull(timeTable.getApptRange(null, firstDay, lastDay));
	  		 assertNotEquals(0, timeTable.getApptRange(appts, firstDay, lastDay).size()); // Makes sure the size of the list is 0
	  		 assertEquals(0, timeTable.getApptRange(appts, firstDay, lastDay).get(0).getSizeAppts()); // Makes sure the 2nd appointment isn't added

				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }
	
}

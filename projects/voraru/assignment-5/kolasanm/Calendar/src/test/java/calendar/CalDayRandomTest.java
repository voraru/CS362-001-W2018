package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

import java.util.*;



import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
		
		private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
		private static final int NUM_TESTS=100;
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	   public void radnomtest()  throws Throwable  {



		 long startTime = Calendar.getInstance().getTimeInMillis();
		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 // Creates the calDay object
		GregorianCalendar cal = new GregorianCalendar();
		CalDay calDay = new CalDay(cal);

		LinkedList<Appt> appts = new LinkedList<Appt>();

		 
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
				 
			 calDay.addAppt(appt); // Adds an appointment with random values to the list 
			 assertNotNull(calDay.getAppts()); // Checks to ensure that the list is not null 

			 if(!appt.getValid())continue;
			
				
				 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0 )
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);

			 
			}
		}catch(NullPointerException e){
			
		}
	 
		 System.out.println("Done testing...");
	 }


	
}

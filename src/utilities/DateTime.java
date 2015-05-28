package utilities;

import java.sql.Date;
import java.util.Calendar;

public class DateTime
{
	private static long advance;
	private long time;
	
	public DateTime()
	{
		time = System.currentTimeMillis() + advance;
	}
	
	public DateTime(int day, int month, int year)
	{
		setDate(day, month, year);
	}
	
	public long getTime()
	{
		return time;
	}
	
	// Advances date/time by specified days, hours and mins for testing purposes
	public static void setAdvance(int days, int hours, int mins)
	{
		advance = ((days * 24L + hours) * 60L) * 60000L;
	}
	
	public String toString()
	{
		long currentTime = getTime();
		Date gct = new Date(currentTime);
		return gct.toString();
	}
	
	public static String getCurrentTime()
	{
		Date date = new Date(System.currentTimeMillis());  // returns current Date/Time
		return date.toString();
	}
	
	// returns difference in days
	public static int diffDays(DateTime dateReturned, DateTime dateHired)
	{
		final long HOURS_IN_DAY = 24L;
		final int MINUTES_IN_HOUR = 60;
		final int SECONDS_IN_MINUTES = 60;
		final int MILLISECONDS_IN_SECOND = 1000;
		long convertToDays = HOURS_IN_DAY * MINUTES_IN_HOUR * SECONDS_IN_MINUTES * MILLISECONDS_IN_SECOND;
		long hirePeriod = dateReturned.getTime() - dateHired.getTime();
		return (int) (1 + (hirePeriod) / (convertToDays));
	}

	private void setDate(int day, int month, int year)
	{
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, 0, 0);   

		java.util.Date date = calendar.getTime();

		time = date.getTime();
	}
}

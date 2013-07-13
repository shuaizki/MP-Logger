package me.shuaizki.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	private static SimpleDateFormat simple_df;
	private static SimpleDateFormat df;
	static {
		simple_df = new SimpleDateFormat("yyyy_MM_dd");
		df = new SimpleDateFormat("HH:mm:ss yyyy_MM_dd");
	}
	
	public static String getSimpleDate()
	{
		Date d = new Date();
		return simple_df.format(d);
	}
	
	public static String getTime()
	{
		Date d = new Date();
		return df.format(d) + " " + d.getTime();
	}
	
	public static Date getNextDay() 
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE,1);
		Date dd = cal.getTime();
		return dd;
	}
}

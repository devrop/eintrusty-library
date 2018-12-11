package com.eintrusty.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static Calendar cal = Calendar.getInstance();
	
	public static String nowOnString() {
		//Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
	}
	public static String dateNowOnString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		
	}
	public static String timeNowOnString() {
		return new SimpleDateFormat("HH:mm;ss").format(cal.getTime());
	}
	public static Date asDate(LocalDateTime localDateTime) {
		    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		  }
	public static Date getNow() {
		return cal.getTime();

	}
}

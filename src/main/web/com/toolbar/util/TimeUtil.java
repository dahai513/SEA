package com.toolbar.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	public static String getTime(){
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return d.format(new Date());
	}
}

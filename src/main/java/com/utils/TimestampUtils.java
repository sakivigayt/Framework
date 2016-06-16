package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {

	/**
	 * @param args
	 */
	public static String getTimeStamp() {
		String fileName = new SimpleDateFormat("yy-MMM-dd hhmmss")
				.format(new Date());
		return fileName;
	}

}

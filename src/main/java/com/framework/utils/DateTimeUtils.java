package com.framework.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author rampourw
 *
 */
public class DateTimeUtils {

	Date date = new Date();

	public Timestamp getTimeStamp() {
		long time = date.getTime();
		return new Timestamp(time);
	}

}

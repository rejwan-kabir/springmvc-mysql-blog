package demo.springmvc.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String getDate(Date date) {
		return SIMPLE_DATE_FORMAT.format(date);
	}
}

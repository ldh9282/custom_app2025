package com.custom.app2025.shared.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtils {

	/**
	 * <pre>
	 * 메서드명: parse
	 * 설명: 스트링을 format 형식의 LocalDateTime 으로 parse 
	 * </pre>
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static LocalDateTime parse(String dateStr, String format) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		return LocalDateTime.parse(dateStr, dtf);
	}
	
	/**
	 * <pre>
	 * 메서드명: format
	 * 설명: LocalDateTime 을 format 형식으로 스트링 반환 
	 * </pre>
	 * @param localDate
	 * @param format
	 * @return
	 */
	public static String format(LocalDateTime localDate, String format) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		return dtf.format(localDate);
	}
}

package com.event.reward.config;

import java.time.format.DateTimeFormatter;

/**
 * 날짜/시간과 관련된 주요 상수 값을 제공합니다.
 */
public class DateTimeConfig {

  public static final String DATE_FORMAT = "yyyy-MM-dd";
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
}

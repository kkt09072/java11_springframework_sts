package com.spring1.util;

import com.github.usingsky.calendar.KoreanLunarCalendar;


public class LunarCalendarUtil {

    public static String solarToLunar(int year, int month, int day) {
        KoreanLunarCalendar calendar = KoreanLunarCalendar.getInstance();
        calendar.setLunarDate(year - 1900, month - 1, day, false);
        return calendar.getLunarIsoFormat();
    }

    public static String lunarToSolar(int year, int month, int day) {
    	KoreanLunarCalendar calendar = KoreanLunarCalendar.getInstance();
    	calendar.setSolarDate(year, month, day);
        return calendar.getSolarIsoFormat();
    }
}

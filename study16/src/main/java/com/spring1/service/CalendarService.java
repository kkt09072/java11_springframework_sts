package com.spring1.service;

import com.spring1.util.LunarCalendarUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CalendarService {

    public Map<String, String> getSolarCalendar() {
        Map<String, String> solarCalendar = new HashMap<>();
        solarCalendar.put("2024-01-01", "양력 2024-01-01");
        solarCalendar.put("2024-01-02", "양력 2024-01-02");
        return solarCalendar;
    }

    public Map<String, String> getLunarCalendar() {
        Map<String, String> lunarCalendar = new HashMap<>();
        lunarCalendar.put("2024-01-01", "음력 " + LunarCalendarUtil.solarToLunar(2024, 1, 1));
        lunarCalendar.put("2024-01-02", "음력 " + LunarCalendarUtil.solarToLunar(2024, 1, 2));
        return lunarCalendar;
    }

    public boolean isReservationAvailable(String date) {
        return !date.equals("2024-01-01");
    }
}
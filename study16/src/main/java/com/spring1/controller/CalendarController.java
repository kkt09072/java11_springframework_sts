package com.spring1.controller;

import com.spring1.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String showCalendar(Model model) {
        model.addAttribute("solarCalendar", calendarService.getSolarCalendar());
        model.addAttribute("lunarCalendar", calendarService.getLunarCalendar());
        return "calendar";
    }

    @RequestMapping(value = "/checkReservation", method = RequestMethod.GET)
    public String checkReservation(@RequestParam("date") String date, Model model) {
        boolean available = calendarService.isReservationAvailable(date);
        model.addAttribute("date", date);
        model.addAttribute("available", available);
        return "reservationResult";
    }
}
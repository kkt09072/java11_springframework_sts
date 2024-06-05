package com.spring1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring1.model.Birthday;
import com.spring1.model.Reservation;
import com.spring1.service.NotificationService;

@Controller
@RequestMapping("/schedule")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/reservation")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "schedule/reservationForm";
    }

    @PostMapping("/reservation")
    public String submitReservation(@ModelAttribute Reservation reservation, Model model) {
        notificationService.scheduleReservation(reservation);
        model.addAttribute("message", "Reservation scheduled successfully!");
        return "schedule/result";
    }

    @GetMapping("/birthday")
    public String showBirthdayForm(Model model) {
        model.addAttribute("birthday", new Birthday());
        return "schedule/birthdayForm";
    }

    @PostMapping("/birthday")
    public String submitBirthday(@ModelAttribute Birthday birthday, Model model) {
        notificationService.scheduleBirthday(birthday);
        model.addAttribute("message", "Birthday notification scheduled successfully!");
        return "schedule/result";
    }
}

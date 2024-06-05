package com.spring1.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class ReservationJob implements Job {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 예약 알림 로직 구현
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@spring1.com");
        message.setSubject("Reservation Reminder");
        message.setText("This is a reminder for your reservation.");

        mailSender.send(message);
    }
}
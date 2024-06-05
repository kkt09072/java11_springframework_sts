package com.spring1.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class BirthdayNotificationJob implements Job {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 생일 알림 로직 구현
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("user@spring1.com");
        message.setSubject("Birthday Notification");
        message.setText("Happy Birthday!");

        mailSender.send(message);
    }
}
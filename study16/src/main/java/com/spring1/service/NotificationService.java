package com.spring1.service;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring1.jobs.BirthdayNotificationJob;
import com.spring1.jobs.ReservationJob;
import com.spring1.model.Birthday;
import com.spring1.model.Reservation;

@Service
public class NotificationService {

    @Autowired
    private Scheduler scheduler;

    public void scheduleReservation(Reservation reservation) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(ReservationJob.class)
                    .withIdentity("reservationJob", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("reservationTrigger", "group1")
                    .startAt(reservation.getDate())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withMisfireHandlingInstructionFireNow())
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void scheduleBirthday(Birthday birthday) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(BirthdayNotificationJob.class)
                    .withIdentity("birthdayJob", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("birthdayTrigger", "group1")
                    .startAt(birthday.getDate())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withMisfireHandlingInstructionFireNow())
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

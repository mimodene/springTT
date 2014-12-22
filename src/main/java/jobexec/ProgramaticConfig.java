package jobexec;

import jobexec.worker.SimpleWorker;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by MMO on 22.12.2014.
 */
@Configuration
public class ProgramaticConfig {

    Date startTime = nextGivenSecondDate(null, 10);

    @Bean
    public Scheduler ipcJobBean() {
        try {
            // First we must get a reference to a scheduler
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();

            JobDetail job1 = getJob1("0");
            SimpleTrigger trigger1 = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startAt(startTime)
                    .withSchedule(simpleSchedule()
                                          .withIntervalInSeconds(10)
                                          .withRepeatCount(4))
                    .build();

            JobDetail job2 = getJob1("1");
            SimpleTrigger trigger2 = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startAt(startTime)
                    .withSchedule(simpleSchedule()
                                          .withIntervalInSeconds(10)
                                          .withRepeatCount(4))
                    .build();

            Date scheduleTime1 = sched.scheduleJob(job1, trigger1);
            System.out.println("Date 1: " + scheduleTime1.toString());

            Date scheduleTime2 = sched.scheduleJob(job2, trigger2);
            System.out.println("Date 2: " + scheduleTime2.toString());
            sched.start();

            return sched;

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JobDetail getJob1(String id) {
        JobDetail job1 = newJob(SimpleWorker.class)
                .withIdentity("job"+id, "group1")
                .build();

        job1.getJobDataMap().put("threadId", id);
        return job1;

    }
}

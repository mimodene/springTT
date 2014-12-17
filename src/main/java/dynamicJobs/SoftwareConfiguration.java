package dynamicJobs;

import acp.ApplicationContextProvider;
import org.quartz.CronTrigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * Created by MMO on 16.12.2014.
 */

public class SoftwareConfiguration {
    ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();

    //get the quartzFactory bean
    CronTriggerFactoryBean cronTriggerFB = (CronTriggerFactoryBean) ctx.getBean("cronTriggerFactoryBean");
    CronTrigger trigger = cronTriggerFB.getObject();


    JobDoSomething job = (JobDoSomething) ctx.getBean("jobDoSomething");


    ThreadPoolTaskExecutor taskExecutor =(ThreadPoolTaskExecutor) ctx.getBean("threadPoolTaskExecutor");
    CronTrigger cronTrigger = new CronTriggerImpl();







    //    //get the task to run or it could have been injected
    //    DataPollingTask dpTask = (DataPollingTask) ctx.getBean(taskName);
    //
    //    //this example uses a simple interval schedule, but could be done with a CRON schedule by using the correct Trigger Bean (CronTriggerBean)
    //    //create job
    //    jobDetail = new MethodInvokingJobDetailFactoryBean();
    //    jobDetail.setTargetObject(dpTask);
    //    jobDetail.setTargetMethod("run");
    //    jobDetail.setName(taskName);
    //    jobDetail.setConcurrent(false);
    //    jobDetail.afterPropertiesSet();
    //
    //    //create trigger
    //    SimpleTriggerBean trigger = new SimpleTriggerBean();
    //    trigger.setBeanName(taskName);
    //    trigger.setJobDetail((JobDetail) jobDetail.getObject());
    //    trigger.setRepeatInterval(interval);
    //    trigger.afterPropertiesSet();
    //
    //    //add to schedule
    //    scheduler.scheduleJob((JobDetail) jobDetail.getObject(), trigger);

}

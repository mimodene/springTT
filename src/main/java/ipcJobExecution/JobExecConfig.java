package ipcJobExecution;

import ipcJobExecution.IPCExecutorImpl.IPCExecutorBuilder;
import ipcJobExecution.helper.DefaultThreadIdGenerator;
import jobexec.worker.SimpleWorker;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MMO on 16.12.2014.
 */
@Configuration
public class JobExecConfig {

    @Bean
    public Scheduler scheduler() {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = sf.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;

    }

    @Bean
    public IPCExecutorImpl firstScheduler() {

        IPCExecutorBuilder builder = new IPCExecutorBuilder();

        IPCExecutorImpl exec = builder.setScheduler(scheduler())
                                      .setCronString("0/5 * * * * ?")
                                      .setWorkerclass(SimpleWorker.class)
                                      .setParallelThreads(2)
                                      .setPauseTimeInSeconds(1)
                                      .setStartDayInSeconds(0)
                                      .setIdGenerator(new DefaultThreadIdGenerator())
                                      .setGroupName("MessagePrints")
                                      .build();
        return exec;
    }

}

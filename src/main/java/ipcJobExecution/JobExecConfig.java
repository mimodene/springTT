package ipcJobExecution;

import ipcJobExecution.IPCExecutorImpl.IPCExecutorBuilder;
import ipcJobExecution.helper.DefaultThreadIdGenerator;
import jobexec.worker.SimpleWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MMO on 16.12.2014.
 */
@Configuration
public class JobExecConfig {

    @Bean
    public IPCExecutorImpl firstScheduler() {

        IPCExecutorBuilder builder = new IPCExecutorBuilder();

        IPCExecutorImpl exec = builder.setCronString(null)
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

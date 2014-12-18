package dynamicJobs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by MMO on 16.12.2014.
 */
@Configuration
@ComponentScan
public class DynamicConfiguration {
    @Bean
    public SchedulerFactoryBean schedulerFactory() {
        return new SchedulerFactoryBean();
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        return new CronTriggerFactoryBean();
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
        exec.setCorePoolSize(5);
        exec.setMaxPoolSize(10);
        exec.setQueueCapacity(2);
        return exec;
    }

}

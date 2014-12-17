package jobexec;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by MMO on 16.12.2014.
 */
@Configuration
@ComponentScan
@EnableScheduling
public class JobExecConfig {

//    @Bean
//    public IPCExecutor schedulerService(){
//        IPCExecutor exec = new IPCExecutor();
//
//        ThreadPoolTaskExecutor springExec = new ThreadPoolTaskExecutor();
//        springExec.setBeanName("schedulerExecutor");
//        springExec.setWaitForTasksToCompleteOnShutdown(true);
//
//        exec.setExecutor(springExec);
//
//
//
//    }
//    @Bean
//    public IPCExecutor multiThreadScheduler(){
//        IPCExecutor exec = new IPCExecutor();
//
//        ThreadPoolTaskExecutor springExec = new ThreadPoolTaskExecutor();
//        springExec.setBeanName("schedulerExecutor");
//        springExec.setWaitForTasksToCompleteOnShutdown(true);
//
//        exec.setExecutor(springExec);
//
//
//
//    }
//    @Bean
//    public IPCExecutor objectStateScheduler(){
//        IPCExecutor exec = new IPCExecutor();
//
//        ThreadPoolTaskExecutor springExec = new ThreadPoolTaskExecutor();
//        springExec.setBeanName("schedulerExecutor");
//        springExec.setWaitForTasksToCompleteOnShutdown(true);
//
//        exec.setExecutor(springExec);
//
//
//
//    }


}

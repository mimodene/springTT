package ipcJobExecution;

import org.springframework.context.annotation.Configuration;

/**
 * Created by MMO on 16.12.2014.
 */
@Configuration
public class JobExecConfig {

    //    @Bean
    //    public IPCExecutor schedulerService() {
    //        IPCExecutor exec = new IPCExecutor();
    //        ThreadPoolTaskExecutor springExec = getPool(1, 1, "schedulerService");
    //        exec.setExecutor(springExec);
    //        exec.setWorkerclass(simpeWorker());
    //        return exec;
    //    }

    //    @Bean
    //    public IPCExecutor multiThreadScheduler() {
    //        IPCExecutor exec = new IPCExecutor();
    //        ThreadPoolTaskExecutor springExec = getPool(10, 10, "multiThreadScheduler");
    //        exec.setExecutor(springExec);
    //        exec.setWorkerclass(complexWorker());
    //        return exec;

    //    }
    //
    //    @Bean
    //    public IPCExecutor objectStateScheduler() {
    //        IPCExecutor exec = new IPCExecutor();
    //        ThreadPoolTaskExecutor springExec = getPool(1, 1, "objectStateScheduler");
    //        exec.setExecutor(springExec);
    //        exec.setWorkerclass(simpeWorker());
    //        return exec;
    //    }

    //    private ThreadPoolTaskExecutor getPool(int maxPoolSize, int corPoolSize, String beanName) {
    //        ThreadPoolTaskExecutor springExec = new ThreadPoolTaskExecutor();
    //        springExec.setBeanName(beanName);
    //        springExec.setWaitForTasksToCompleteOnShutdown(true);
    //        springExec.setCorePoolSize(corPoolSize);
    //        springExec.setMaxPoolSize(maxPoolSize);
    //        return springExec;
    //    }

}

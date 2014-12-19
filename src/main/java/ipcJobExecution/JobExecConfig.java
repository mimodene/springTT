package ipcJobExecution;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MMO on 16.12.2014.
 */
@Configuration
public class JobExecConfig {

        @Bean
        public IPCExecutor firstScheduler() {
            IPCExecutor exec = new IPCExecutorImpl();
            exec.setStatusAggregator(new StatusAggregatorImpl());
            return exec;
        }


    //    private ThreadPoolTaskExecutor getPool(int maxPoolSize, int corPoolSize, String beanName) {
    //        ThreadPoolTaskExecutor springExec = new ThreadPoolTaskExecutor();
    //        springExec.setBeanName(beanName);
    //        springExec.setWaitForTasksToCompleteOnShutdown(true);
    //        springExec.setCorePoolSize(corPoolSize);
    //        springExec.setMaxPoolSize(maxPoolSize);
    //        return springExec;
    //    }

}

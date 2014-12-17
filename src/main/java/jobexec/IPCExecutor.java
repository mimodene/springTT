package jobexec;

import jobexec.worker.WorkerClass;
import org.quartz.Scheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by MMO on 16.12.2014.
 */
public class IPCExecutor {

    private ThreadPoolTaskExecutor executor;
    private WorkerClass workerclass;
    private Scheduler scheduler;

    public ThreadPoolTaskExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(final ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    public WorkerClass getWorkerclass() {
        return workerclass;
    }

    public void setWorkerclass(final WorkerClass workerclass) {
        this.workerclass = workerclass;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}

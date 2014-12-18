package ipcJobExecution;

import jobexec.worker.Worker;
import org.quartz.Scheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by MMO on 16.12.2014.
 */
public class IPCExecutorImpl implements IPCExecutor {

    private ThreadPoolTaskExecutor executor;
    private Worker workerclass;
    private Scheduler scheduler;
    private ThreadIdGenerator idGenerator;

    @Override
    public ThreadIdGenerator getIdGenerator() {
        return idGenerator;
    }

    @Override
    public void setIdGenerator(final ThreadIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public void startAll() {
    }

    @Override
    public void stopAll() {
        executor.shutdown();
    }

    @Override
    public void requestStop() {

    }

    @Override
    public ThreadPoolTaskExecutor getExecutor() {
        return executor;
    }

    @Override
    public void setExecutor(final ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    @Override
    public Worker getWorkerclass() {
        return workerclass;
    }

    @Override
    public void setWorkerclass(final Worker workerclass) {
        this.workerclass = workerclass;
    }

    @Override
    public Scheduler getScheduler() {
        return scheduler;
    }

    @Override
    public void setScheduler(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void setStatusAggregator(final StatusAggregator theStatusAggregator) {

    }

    @Override
    public StatusAggregator getStatusGgregator() {
        return null;
    }
}

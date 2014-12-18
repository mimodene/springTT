package ipcJobExecution;

import jobexec.worker.Worker;
import org.quartz.Scheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by MMO on 18.12.2014.
 */
public interface IPCExecutor {

    public  ThreadIdGenerator getIdGenerator();

    public  void setIdGenerator(ThreadIdGenerator idGenerator);

    public  void startAll();

    public  void stopAll();

    public void requestStop();

    public  ThreadPoolTaskExecutor getExecutor();

    public  void setExecutor(ThreadPoolTaskExecutor executor);

    public  Worker getWorkerclass();

    public   void setWorkerclass(Worker workerclass);

    public   Scheduler getScheduler();

    public   void setScheduler(Scheduler scheduler);

    public  void setStatusAggregator(StatusAggregator theStatusAggregator);

    public StatusAggregator getStatusGgregator();


}

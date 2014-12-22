package ipcJobExecution;

import ipcJobExecution.helper.ServiceState;
import ipcJobExecution.helper.ThreadIdGenerator;
import jobexec.worker.Worker;

/**
 * Created by MMO on 18.12.2014.
 */
public interface IPCExecutor {

    public ThreadIdGenerator getIdGenerator();

    public  void setIdGenerator(ThreadIdGenerator idGenerator);

    public  void startAll();

    public  void stopAll();

    public void requestStop();

    public  Worker getWorkerclass();

    public   void setWorkerclass(Worker workerclass);

    public  void setServiceState(ServiceState theStatusAggregator);




}

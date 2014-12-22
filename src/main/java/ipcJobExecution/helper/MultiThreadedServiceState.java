package ipcJobExecution.helper;

import java.util.List;

/**
 * Created by MMO on 22.12.2014.
 */
public interface MultiThreadedServiceState {
    void setNumberOfThreads(int numberOfThreads);

    int getNumberOfThreads();

    List<ServiceState> getThreadServiceStates();

    void setProcessing(int threadId, boolean processing);

    void setShutdown(int threadId, boolean shutdown);

    boolean isRunning(int threadId);

    boolean isStopRequested(int threadId);

    boolean isResetRequested(int threadId);

    void setAllRunning(boolean running);

    long getLastProcessTime(int threadId);

    void setLastProcessTime(int threadId, long lastProcessTime);

    long getNextExecution(int threadId);

    void setNextExecution(int threadId, long nextExecution);

    boolean allThreadsStopped();

    boolean allThreadsStopping();

    void requestStopAllThreads();

    boolean allThreadsShutdown();

    boolean allThreadsShuttingDown();

    void requestShutdownAllThreads();

    long getNextExecution();

    public ServiceState getServiceState(int threadId);
}

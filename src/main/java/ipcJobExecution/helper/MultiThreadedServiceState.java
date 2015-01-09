package ipcJobExecution.helper;

import java.util.List;

/**
 * Created by MMO on 22.12.2014.
 */
public interface MultiThreadedServiceState {
    void putServiceState(Object theKey, ServiceState theServiceState);

    void setNumberOfThreads(int numberOfThreads);

    int getNumberOfThreads();

    List<ServiceState> getThreadServiceStates();

    void setProcessing(Object threadId, boolean processing);

    void setShutdown(Object threadId, boolean shutdown);

    boolean isRunning(Object threadId);

    boolean isStopRequested(Object threadId);

    boolean isResetRequested(Object threadId);

    void setAllRunning(boolean running);

    long getLastProcessTime(Object threadId);

    void setLastProcessTime(Object threadId, long lastProcessTime);

    long getNextExecution(Object threadId);

    void setNextExecution(Object threadId, long nextExecution);

    boolean allThreadsStopped();

    boolean allThreadsStopping();

    void requestStopAllThreads();

    boolean allThreadsShutdown();

    boolean allThreadsShuttingDown();

    void requestShutdownAllThreads();

    long getNextExecution();

    void reset();
}

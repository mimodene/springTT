package ipcJobExecution.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p/>
 * Copyright by Incentage AG, Fehraltorf, Switzerland, <a target="_blank" href="http://www.incentage.com">http://www.incentage.com</a><br>
 */
public class ServiceStateHolder implements MultiThreadedServiceState {

    private Map<Object, ServiceState> serviceStates;
    private int numberOfThreads;

    @Override
    public void putServiceState(Object theKey, ServiceState theServiceState) {
        if (serviceStates == null) {
            serviceStates = new HashMap<Object, ServiceState>();
        }
        serviceStates.put(theKey, theServiceState);
    }

    @Override
    public void setNumberOfThreads(final int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    @Override
    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    @Override
    public List<ServiceState> getThreadServiceStates() {
        return (ArrayList<ServiceState>) serviceStates.values();
    }

    @Override
    public void setProcessing(final Object threadId, final boolean processing) {
        getServiceState(threadId).setProcessing(processing);
    }

    @Override
    public void setShutdown(final Object threadId, final boolean shutdown) {
        getServiceState(threadId).setShutdown(shutdown);
    }

    @Override
    public boolean isRunning(final Object threadId) {
        return getServiceState(threadId).isRunning();
    }

    @Override
    public boolean isStopRequested(final Object threadId) {
        return getServiceState(threadId).isStopRequested();
    }

    @Override
    public boolean isResetRequested(final Object threadId) {
        return getServiceState(threadId).isResetRequested();
    }

    @Override
    public void setAllRunning(final boolean running) {
        for (ServiceState state : serviceStates.values()) {
            state.setRunning(running);
        }
    }

    @Override
    public long getLastProcessTime(final Object threadId) {
        return getServiceState(threadId).getLastProcessTime();
    }

    @Override
    public void setLastProcessTime(final Object threadId, final long lastProcessTime) {
        getServiceState(threadId).setLastProcessTime(lastProcessTime);
    }

    @Override
    public long getNextExecution(final Object threadId) {
        return 0;
    }

    @Override
    public void setNextExecution(final Object threadId, final long nextExecution) {

    }

    @Override
    public boolean allThreadsStopped() {
        for (ServiceState serviceState : serviceStates.values()) {
            if (serviceState.isRunning()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean allThreadsStopping() {
        for (ServiceState serviceState : serviceStates.values()) {
            if (!serviceState.isStopRequested() && serviceState.isRunning()) {
                // no stop requested and not stopped
                return false;
            }
        }
        return true;
    }

    @Override
    public void requestStopAllThreads() {
        for (ServiceState serviceState : serviceStates.values()) {
            serviceState.setStopRequested();
        }
    }

    @Override
    public boolean allThreadsShutdown() {
        for (ServiceState serviceState : serviceStates.values()) {
            if (!serviceState.isShutdown()) {
                // no shutdown requested and not shut down
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean allThreadsShuttingDown() {
        for (ServiceState serviceState : serviceStates.values()) {
            if (!serviceState.isShutdownRequested() && !serviceState.isShutdown()) {
                // no shutdown requested and not shut down
                return false;
            }
        }
        return true;
    }

    @Override
    public void requestShutdownAllThreads() {
        for (ServiceState state : serviceStates.values()) {
            state.setShutdownRequested();
        }

    }

    @Override
    public long getNextExecution() {
        return 0;
    }

    @Override
    public void reset() {
        for (ServiceState state : serviceStates.values()) {
            System.out.println("call reset on: " + state.toString());
            state.setLastProcessTime(0);
        }
    }

    private ServiceState getServiceState(Object theId) {
        if (serviceStates == null) {
            throw new NullPointerException();

        }
        ServiceState state = serviceStates.get(theId);
        if (state == null) {
            throw new NullPointerException();
        }
        return state;

    }

}
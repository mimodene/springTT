package ipcJobExecution.helper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by MMO on 22.12.2014.
 */
public class MultiThreadedServiceStateImpl implements MultiThreadedServiceState {

    private static final long serialVersionUID = -1349728060370143699L;
    private final MyServiceState serviceState = new MyServiceState();
    private Map<Integer, ServiceState> threadServiceStates = new Hashtable<Integer, ServiceState>();

    @Override
    public void setNumberOfThreads(int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            getServiceState(i);
        }
    }

    @Override
    public int getNumberOfThreads() {
        return threadServiceStates.size();
    }

    @Override
    public List<ServiceState> getThreadServiceStates() {
        return new ArrayList<ServiceState>(threadServiceStates.values());
    }

    @Override
    public ServiceState getServiceState(int threadId) {
        ServiceState serviceState = threadServiceStates.get(threadId);
        if (serviceState == null) {
            serviceState = new ServiceState();
            threadServiceStates.put(threadId, serviceState);
        }
        return serviceState;
    }

    @Override
    public void setProcessing(int threadId, boolean processing) {
        getServiceState(threadId).setProcessing(processing);
    }

    @Override
    public void setShutdown(int threadId, boolean shutdown) {
        getServiceState(threadId).setShutdown(shutdown);
    }

    @Override
    public boolean isRunning(int threadId) {
        return getServiceState(threadId).isRunning();
    }

    @Override
    public boolean isStopRequested(int threadId) {
        return getServiceState(threadId).isStopRequested();
    }

    @Override
    public boolean isResetRequested(int threadId) {
        return getServiceState(threadId).isResetRequested();
    }

    @Override
    public void setAllRunning(boolean running) {
        for (ServiceState serviceState : threadServiceStates.values()) {
            serviceState.setRunning(running);
        }
    }

    @Override
    public long getLastProcessTime(int threadId) {
        return getServiceState(threadId).getLastProcessTime();
    }

    @Override
    public void setLastProcessTime(int threadId, long lastProcessTime) {
        getServiceState(threadId).setLastProcessTime(lastProcessTime);
    }

    @Override
    public long getNextExecution(int threadId) {
        return getServiceState(threadId).getNextExecution();
    }

    @Override
    public void setNextExecution(int threadId, long nextExecution) {
        getServiceState(threadId).setNextExecution(nextExecution);
    }

    @Override
    public boolean allThreadsStopped() {
        for (ServiceState serviceState : threadServiceStates.values()) {
            if (serviceState.isRunning()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all threads are either stopped or have stop requested
     *
     * @return boolean
     */
    @Override
    public boolean allThreadsStopping() {
        for (ServiceState serviceState : threadServiceStates.values()) {
            if (!serviceState.isStopRequested() && serviceState.isRunning()) {
                // no stop requested and not stopped
                return false;
            }
        }
        return true;
    }

    @Override
    public void requestStopAllThreads() {
        for (ServiceState serviceState : threadServiceStates.values()) {
            serviceState.setStopRequested();
        }
    }

    @Override
    public boolean allThreadsShutdown() {
        for (ServiceState serviceState : threadServiceStates.values()) {
            if (!serviceState.isShutdown()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean allThreadsShuttingDown() {
        for (ServiceState serviceState : threadServiceStates.values()) {
            if (!serviceState.isShutdownRequested() && !serviceState.isShutdown()) {
                // no shutdown requested and not shut down
                return false;
            }
        }
        return true;
    }

    @Override
    public void requestShutdownAllThreads() {
        for (ServiceState serviceState : threadServiceStates.values()) {
            serviceState.setShutdownRequested();
        }
    }

    public boolean isProcessing() {
        return serviceState.isProcessing();
    }

    public void setProcessing(boolean processing) {
        serviceState.setProcessing(processing);
    }

    public boolean isShutdown() {
        return serviceState.isShutdown();
    }

    public void setShutdown(boolean shutdown) {
        serviceState.setShutdown(shutdown);
    }

    public boolean isRunning() {
        return serviceState.isRunning();
    }

    public void setRunning(boolean running) {
        serviceState.setRunning(running);
    }

    public boolean isResetRequested() {
        return serviceState.isResetRequested();
    }

    public void setResetRequested() {
        serviceState.setResetRequested();
    }

    public void setStopRequested() {
        serviceState.setStopRequested();
    }

    public boolean isStopRequested() {
        return serviceState.isStopRequested();
    }

    public void setShutdownRequested() {
        serviceState.setShutdownRequested();
    }

    public boolean isShutdownRequested() {
        return serviceState.isShutdownRequested();
    }

    public void resetCommand() {
        serviceState.resetCommand();
    }

    public long getLastProcessTime() {
        return serviceState.getLastProcessTime();
    }

    public void setLastProcessTime(long lastProcessTime) {
        serviceState.setLastProcessTime(lastProcessTime);
    }

    public long getNextExecution() {
        return serviceState.getNextExecution();
    }

    public void setNextExecution(long nextExecution) {
        serviceState.setNextExecution(nextExecution);
    }

    public int getState() {
        return serviceState.getState();
    }

    public int getCommand() {
        return serviceState.getCommand();
    }

    public int getHealth() {
        return serviceState.getHealth();
    }

    public void setHealth(int health) {
        serviceState.setHealth(health);
    }

    public List<String> getLastMessages() {
        return serviceState.getLastMessages();
    }

    public void clearMessages() {
        serviceState.clearMessages();
    }

    public void addMessage(String message) {
        serviceState.addMessage(message);
    }

    public long getLastResetTime() {
        return serviceState.getLastResetTime();
    }

    public void setLastResetTime(long lastResetTime) {
        serviceState.setLastResetTime(lastResetTime);
    }

    private class MyServiceState extends ServiceState {
        @Override
        public long getNextExecution() {
            return 0;
        }
    }
}

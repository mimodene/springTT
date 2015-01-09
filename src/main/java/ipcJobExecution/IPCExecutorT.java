package ipcJobExecution;

import ipcJobExecution.helper.ServiceState;
import org.quartz.SchedulerException;

import java.util.Map;

/**
 * <p/>
 * Copyright by Incentage AG, Fehraltorf, Switzerland, <a target="_blank" href="http://www.incentage.com">http://www.incentage.com</a><br>
 */
public interface IPCExecutorT {

    void buildExecutor() throws SchedulerException;

    void start() throws SchedulerException;

    void stop();

    void reset();

    Map<Object, ServiceState>  getServiceStates();


}

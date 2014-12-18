package ipcJobExecution;

/**
 * Created by MMO on 18.12.2014.
 */
public interface StatusAggregator {

    public static final int RUNNING = 0;
    public static final int STOP = 1;
    public static final int START = 2;
    public static final int STOP_REQUEST = 3;
    public static final int STOPPING = 4;
    public static final int STARTING = 5;

    public void getStatus();

    public void setStatusTo(int theNewStatus);

}

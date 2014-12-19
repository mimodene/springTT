package ipcJobExecution;

/**
 * Created by MMO on 19.12.2014.
 */
public class StatusAggregatorImpl implements StatusAggregator {

    private int currentstatus =0;

    @Override
    public int getStatus() {
        return currentstatus;
    }

    @Override
    public void setStatusTo(final int theNewStatus) {
        currentstatus=theNewStatus;

    }
}

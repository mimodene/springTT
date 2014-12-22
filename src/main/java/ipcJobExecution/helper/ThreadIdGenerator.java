package ipcJobExecution.helper;

/**
 * Created by MMO on 17.12.2014.
 */
public interface ThreadIdGenerator {

    public String getNextId();
    public void resetId();
}

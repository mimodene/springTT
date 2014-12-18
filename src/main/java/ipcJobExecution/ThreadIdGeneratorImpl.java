package ipcJobExecution;

/**
 * Created by MMO on 17.12.2014.
 */
public class ThreadIdGeneratorImpl implements ThreadIdGenerator {

    private int nextId = 0;

    @Override
    public synchronized int getThreadId() {
        int id = nextId;
        nextId++;
        return id;
    }
}

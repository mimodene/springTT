package ipcJobExecution.helper;

/**
 * Created by MMO on 22.12.2014.
 */
public class DefaultThreadIdGenerator implements ThreadIdGenerator {

    private int id =0;

    public synchronized  String getNextId(){
        String nextId = String.valueOf(id);
        id++;
        return  nextId;
    }

    public synchronized void resetId(){
        id=0;

    }
}

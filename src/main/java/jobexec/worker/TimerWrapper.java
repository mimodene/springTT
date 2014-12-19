package jobexec.worker;

import java.util.TimerTask;

/**
 * Created by MMO on 19.12.2014.
 */
public class TimerWrapper extends TimerTask {

    private SimpleWorker worker;

    public void setSimpleWorker(SimpleWorker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        worker.saySomething();
    }
}

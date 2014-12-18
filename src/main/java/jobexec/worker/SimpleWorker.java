package jobexec.worker;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by MMO on 16.12.2014.
 */
public class SimpleWorker extends QuartzJobBean implements Worker {

    private Params params;
    private int counter;
    private long lastcheck = System.currentTimeMillis();
    private String name;

    public void setParams(Params theParams) {
        this.params = theParams;
    }

    public void setName(String theName) {
        this.name = theName;

    }

    @Override
    public void saySomething() {
        System.out.println(" ++++++++++++++++++ Say something with ID: " + params.getId() + " counter: " + counter++ + " lastcheck :" + (System.currentTimeMillis() - lastcheck));
        lastcheck = System.currentTimeMillis();
    }

    @Override
    protected void executeInternal(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String threadId = jobExecutionContext.getMergedJobDataMap().getString("threadId");
        System.out.println("ThreadId = " + threadId + " Say something with name: " + name + " counter: " + counter++ + " lastcheck :" + (System.currentTimeMillis() - lastcheck));
        lastcheck = System.currentTimeMillis();
    }
}

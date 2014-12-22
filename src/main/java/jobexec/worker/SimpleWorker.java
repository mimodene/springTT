package jobexec.worker;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * Created by MMO on 16.12.2014.
 */
@DisallowConcurrentExecution
public class SimpleWorker extends QuartzJobBean implements Worker {

    private Params params;
    private int counter;
    private static long lastcheck = System.currentTimeMillis();
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

        System.out.println(" ThreadId = " + threadId + "   Call entry time : " + new Date());
        lastcheck = System.currentTimeMillis();

        int threadsleeper = Integer.valueOf(threadId);

        int sleep;
        if (threadId.equals("0")) {
            sleep = 12000;
        } else {
            sleep = threadsleeper * 10000;
        }

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" ThreadId = " + threadId + "   Next fire time: " + jobExecutionContext.getTrigger().getFireTimeAfter(new Date()));

    }
}

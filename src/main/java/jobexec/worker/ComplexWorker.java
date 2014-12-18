package jobexec.worker;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Calendar;

/**
 * Created by MMO on 16.12.2014.
 */
public class ComplexWorker extends QuartzJobBean implements Worker {

    private Params params;
    private int counter;
    private long lastcheck = System.currentTimeMillis();

    public void setParams(Params theParams) {
        this.params = theParams;
    }

    @Override
    public void saySomething() {
        System.out.println("--------------Say something first call " + params.getId() +" counter: "+counter++  +" lastcheck :" + (System.currentTimeMillis() - lastcheck)+ " : "+ Calendar.getInstance().getTime());
        lastcheck= System.currentTimeMillis();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------Say something after sleep 2000 " + params.getId() +" counter: "+counter++  +" lastcheck :" + (System.currentTimeMillis() - lastcheck)+ " : "+ Calendar.getInstance().getTime());
        lastcheck= System.currentTimeMillis();

    }

    @Override
    protected void executeInternal(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Execute job with ID: " + params.getId());
    }
}

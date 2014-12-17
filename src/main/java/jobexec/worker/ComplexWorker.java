package jobexec.worker;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by MMO on 16.12.2014.
 */
public class ComplexWorker  extends QuartzJobBean implements  Worker  {

    private Params params;


    public void setParams(Params theParams) {
        this.params = theParams;
    }

    @Override
    public void saySomething() {
        System.out.println("Say something with ID: "+ params.getId());
    }

    @Override
    protected void executeInternal(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Execute job with ID: "+ params.getId());
    }
}

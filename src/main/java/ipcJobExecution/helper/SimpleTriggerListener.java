package ipcJobExecution.helper;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

/**
 * <p/>
 * Copyright by Incentage AG, Fehraltorf, Switzerland, <a target="_blank" href="http://www.incentage.com">http://www.incentage.com</a><br>
 */
public class SimpleTriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return "Listener";
    }

    @Override
    public void triggerFired(final Trigger trigger, final JobExecutionContext jobExecutionContext) {

    }

    @Override
    public boolean vetoJobExecution(final Trigger trigger, final JobExecutionContext jobExecutionContext) {
        return false;
    }

    @Override
    public void triggerMisfired(final Trigger trigger) {
        System.out.println("Missfired !!!!!!!!!!!! !!! see Trigger : "+ trigger.getJobDataMap().toString());

    }

    @Override
    public void triggerComplete(final Trigger trigger, final JobExecutionContext jobExecutionContext, final CompletedExecutionInstruction completedExecutionInstruction) {
       // System.out.println("triggerComplete !!! see Trigger : "+ trigger.getJobDataMap().toString());
    }
}
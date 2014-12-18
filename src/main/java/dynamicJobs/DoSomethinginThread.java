package dynamicJobs;

import org.springframework.core.task.TaskExecutor;

/**
 * Created by MMO on 16.12.2014.
 */

public class DoSomethinginThread {

    private TaskExecutor taskExecutor;
    private JobDoSomething classWithMethodToFire;

    public DoSomethinginThread(TaskExecutor taskExecutor, JobDoSomething classWithMethodToFire) {
        this.taskExecutor = taskExecutor;
        this.classWithMethodToFire = classWithMethodToFire;
    }

    public void fire(final SomeParameterClass parameter) {
        taskExecutor.execute(new Runnable() {
            public void run() {
                classWithMethodToFire.doSomething(parameter);
            }
        });
    }

}

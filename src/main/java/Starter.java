import acp.ApplicationContextProvider;
import addremovebean.TestAddRemove;
import config.Doer;
import config.Dosomething;
import config.NotDoer;
import gui.MainFrame;
import ipcJobExecution.IPCExecutorImpl;
import ipcJobExecution.JobExecConfig;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import scheduler.ScheduledDoer;

/**
 * Created by MMO on 12.12.2014.
 */

public class Starter {

    public static void main(String args[]) {
        Starter start = new Starter();
        final MainFrame mainFrame = new MainFrame();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                mainFrame.setVisible(true);
            }
        });

        start.go();

    }

    private void go() {
        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext(JobExecConfig.class);

        testProgrammatic(ctx);
        //   testParallel();
        //   testBasic(ctx);
        //   testDynamic(ctx);
        //   testAddRemove();
        // ((ConfigurableApplicationContext) ctx).close();
    }

    private void testProgrammatic(final ApplicationContext ctx) {

        IPCExecutorImpl impl = (IPCExecutorImpl) ctx.getBean("firstScheduler");
        try {
            impl.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    private void testParallel() {

    }

    private void testAddRemove() {
        TestAddRemove testaddremove = new TestAddRemove();
        testaddremove.test();
    }

    private void testBasic(ApplicationContext ctx) {
        Dosomething myService = ctx.getBean(Doer.class);
        myService.go();
        Dosomething notdoer = ctx.getBean(NotDoer.class);
        notdoer.go();
        EntryClass entry = new EntryClass();
        entry.go();
    }

    private void testDynamic(ApplicationContext ctx) {
        ScheduledDoer doer = ctx.getBean(ScheduledDoer.class);
        doer.getController().status = 1;
        doer.getController().status = 0;

    }
}

import acp.ApplicationContextProvider;
import addremovebean.TestAddRemove;
import config.Doer;
import config.Dosomething;
import config.NotDoer;
import jobexec.ComplexInvokerConfig;
import jobexec.SimpleInvokerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import scheduler.ScheduledDoer;

/**
 * Created by MMO on 12.12.2014.
 */

public class Starter {

    public static void main(String args[]) {
        Starter start = new Starter();
        start.go();
    }

    private void go() {
        //  ApplicationContext ctx = ApplicationContextProvider.getApplicationContext(AddRemoveConfig.class);

        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext(ComplexInvokerConfig.class, SimpleInvokerConfig.class
                                                                                 );

        testParallel();

        //   testBasic(ctx);
        //   testDynamic(ctx);
        //   testAddRemove();
        ((ConfigurableApplicationContext) ctx).close();
    }

    private void testParallel() {

        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ScheduledDoer doer = ctx.getBean(ScheduledDoer.class);

        doer.getController().status = 1;

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doer.getController().status = 0;
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

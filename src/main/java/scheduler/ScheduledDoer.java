package scheduler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by MMO on 16.12.2014.
 */
@Component
public class ScheduledDoer implements ApplicationListener<ContextClosedEvent> {

    private long lastTime = System.currentTimeMillis();
    private long lastDoSmtElse = System.currentTimeMillis();

    private Controller cont = new Controller();

    @Scheduled(cron = "*/2 * * * * ?")
    public void showTimer() {

        if(cont.status==0) {
            System.out.println("showTimer, no action, status = 0 " + (System.currentTimeMillis() - lastTime));
        }else{
            System.out.println(" showTimer, doing some funny things here, status <> 0 " + (System.currentTimeMillis() - lastTime));
        }

        lastTime = System.currentTimeMillis();
        throw new RuntimeException();

    }
    @Scheduled(cron = "*/5 * * * * ?")
    public void doSomethingElse() {
        if(cont.status==0) {
            System.out.println("doSomethingElse no action, status = 0  " + (System.currentTimeMillis() - lastDoSmtElse));
        }else{
            System.out.println("doSomethingElse, doing some funny things here, status <> 0 " + (System.currentTimeMillis() - lastDoSmtElse));
        }
        lastTime = System.currentTimeMillis();

    }

    @Override
    public void onApplicationEvent(final ContextClosedEvent contextClosedEvent) {
        ApplicationContext con = contextClosedEvent.getApplicationContext();
        System.out.println("------AppContext to close, cleanup start ::");
        cont.status=1;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------Cleanup end");
    }


    public void setController(Controller theCont){
        this.cont = theCont;
    }

    public Controller getController(){
        return cont;
    }
}

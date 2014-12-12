import config.Doer;
import config.Dosomething;
import config.NotDoer;
import org.springframework.context.ApplicationContext;
import provider.ApplicationContextProvider;

/**
 * Created by MMO on 12.12.2014.
 */

public class Starter {

    public static void main(String args[]) {
        System.out.print(" here we go....)");

        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();

        Dosomething myService = ctx.getBean(Doer.class);
        myService.go();

        Dosomething notdoer = ctx.getBean(NotDoer.class);
        notdoer.go();

        EntryClass entry = new EntryClass();
        entry.go();
    }
}

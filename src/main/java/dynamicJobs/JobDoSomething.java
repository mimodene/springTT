package dynamicJobs;

import org.springframework.stereotype.Component;

/**
 * Created by MMO on 16.12.2014.
 */
@Component(value = "jobDoSomething")
public class JobDoSomething {

    public void doSomething(SomeParameterClass parameter) {
        System.out.println("now i m doing something...." + parameter.getName() + " " + parameter.getNumber());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("now i m done....");
    }
}

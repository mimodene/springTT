package config;

import org.springframework.stereotype.Component;

/**
 * Created by MMO on 12.12.2014.
 */

@Component
public class Innerautowired {

    private PlainDoer plainDoer = new PlainDoer();

    public void sayThis() {
        System.out.println("Innerdoer sayThis: " + toString());
        plainDoer.doSomeThing();
    }
}

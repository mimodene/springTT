package config;

import org.springframework.stereotype.Component;

/**
 * Created by MMO on 12.12.2014.
 */

@Component
public class NotDoer implements Dosomething {

    @Override
    public void go() {
        System.out.println("not doing anything: " + toString());
    }
}

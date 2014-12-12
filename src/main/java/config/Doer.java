package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by MMO on 12.12.2014.
 */

@Component
public class Doer implements Dosomething {
    @Autowired
    AutowiredClass auto;


    public void go() {
        System.out.println("real doer: " + toString());
        auto.go();
    }
}

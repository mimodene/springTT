package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by MMO on 12.12.2014.
 */
@Component
public class AutowiredClass implements Dosomething {

    @Autowired
    private Innerautowired innerAutowired;

    @Override
    public void go() {
        System.out.println("AutowiredClass go : " + toString());
        innerAutowired.sayThis();
    }
}

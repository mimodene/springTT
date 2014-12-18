package config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by MMO on 12.12.2014.
 */

@Component
public class NotDoer implements Dosomething, ApplicationListener<ContextClosedEvent> {

    @Override
    public void go() {
        System.out.println("not doing anything: " + toString());
    }

    @Override
    public void onApplicationEvent(final ContextClosedEvent contextClosedEvent) {
        System.out.println("--------Shutdown the NotDoer: " + toString());
    }
}

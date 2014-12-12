package provider;

import config.BeanConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by MMO on 12.12.2014.
 */
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        if (arg0 != null) {
            ctx = arg0;
            System.out.println("cxt created");
        }
    }

    public synchronized static ApplicationContext getApplicationContext() {
        if (ctx == null) {
             ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
        }
        return ctx;
    }
}
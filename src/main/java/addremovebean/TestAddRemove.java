package addremovebean;

import acp.ApplicationContextProvider;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by MMO on 17.12.2014.
 */
public class TestAddRemove {

    public void test() {
        AnnotationConfigApplicationContext ctx = (AnnotationConfigApplicationContext) ApplicationContextProvider.getApplicationContext();
        DemoBean demo = (DemoBean) ctx.getBean("demo");

        if (demo != null) {
            System.out.println("Demo is not null....." + demo.toString());
            demo.saySomething();
        }

        ConfigurableListableBeanFactory configurableListableBeanFactory = ctx.getBeanFactory();
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) configurableListableBeanFactory;
        // Removing the bean from container
        beanDefinitionRegistry.removeBeanDefinition("demo");
        // Trying to obtains bean again from container. This will throw a null
        demo = getBean(ctx);
        //demo object will be null here

        if (demo == null) {
            System.out.println("Demo is  null.....");
        }

        BeanDefinition beanDefinition = new RootBeanDefinition(DemoBean.class);
        beanDefinition.setAttribute("name", "demo");

        beanDefinitionRegistry.registerBeanDefinition("demo", beanDefinition);
        demo = getBean(ctx);

        if (demo != null) {
            System.out.println("Demo is not null....." + demo.toString());
            demo.saySomething();
        }

    }

    private static DemoBean getBean(AnnotationConfigApplicationContext applicationContext) {
        try {
            return (DemoBean) applicationContext.getBean("demo");
        } catch (NoSuchBeanDefinitionException e) {
            return null;
        }
    }

}

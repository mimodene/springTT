package jobexec;

import jobexec.worker.Params;
import jobexec.worker.SimpleWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * Created by MMO on 17.12.2014.
 */
@Configuration
public class SimpleInvokerConfig {

    @Bean
    public SchedulerFactoryBean schedulerSimpleFactoryBean(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setBeanName("schedulerFactoryBean");
        bean.setJobDetails(simpleInvoking().getObject());
        bean.setTriggers(simpleTrigger().getObject());
        return bean;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean simpleInvoking(){
        MethodInvokingJobDetailFactoryBean invoker = new MethodInvokingJobDetailFactoryBean();
        invoker.setBeanName("simpleInvoking");
        invoker.setTargetBeanName("simpleWorker");
        invoker.setTargetMethod("saySomething");
        return invoker;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTrigger(){
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(simpleInvoking().getObject());
        trigger.setName("simpleTrigger");
        trigger.setStartDelay(1000);
        trigger.setRepeatInterval(2000);
        return trigger;

    }
    @Bean
    public SimpleWorker simpleWorker() {
        SimpleWorker simple = new SimpleWorker();
        Params param = new Params();
        param.setId("0");
        simple.setParams(param);
        return simple;
    }
}

package jobexec;

import jobexec.worker.ComplexWorker;
import jobexec.worker.Params;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by MMO on 17.12.2014.
 */
@Configuration
public class ComplexInvokerConfig {

    @Bean
    public SchedulerFactoryBean schedulerComplexFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setBeanName("schedulerComplexFactoryBean");
        bean.setJobDetails(complexInvoking().getObject());
        bean.setTriggers(cronTrigger().getObject());
        return bean;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean complexInvoking() {
        MethodInvokingJobDetailFactoryBean invoker = new MethodInvokingJobDetailFactoryBean();
        invoker.setBeanName("complexInvoking");
        invoker.setTargetBeanName("complexWorker");
        invoker.setTargetMethod("saySomething");
        return invoker;
    }

    @Bean
    public CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(complexInvoking().getObject());
        trigger.setCronExpression("*/10 * * * * ?");
        return trigger;
    }

    @Bean
    public ComplexWorker complexWorker() {
        ComplexWorker complex = new ComplexWorker();
        Params param = new Params();
        param.setId("1");
        complex.setParams(param);
        return complex;
    }

}

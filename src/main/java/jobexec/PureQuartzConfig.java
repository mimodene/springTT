package jobexec;

import jobexec.worker.ComplexWorker;
import jobexec.worker.SimpleWorker;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PureQuartzConfig {

    @Bean
    public SchedulerFactoryBean parallelSchedulerFactoryBean() {

        boolean parallel = false;
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //        JobDetailFactoryBean jfBean = jobFactoryBean();
        //        bean.setJobDetails(jfBean.getObject());
        bean.setBeanName("parallelSchedulerFactoryBean");
        if(parallel) {
            List<SimpleTrigger> alltriggers = simpleTriggers();
            SimpleTrigger[] arr = alltriggers.toArray(new SimpleTrigger[alltriggers.size()]);
            bean.setTriggers(arr);
        }else {
            bean.setTriggers(firstTrigger().getObject(), secondTrigger().getObject(), thirdTrigger().getObject());
        }
        return bean;
    }

    @Bean
    public List<SimpleTrigger> simpleTriggers() {
        List<SimpleTrigger> alltriggers = new ArrayList<SimpleTrigger>();
        JobDetail jbDetail = jobFactoryBean().getObject();

        for (int i = 0; i < 10; i++) {
            SimpleTrigger trigger = new SimpleTriggerImpl();

//            trigger.(1000);
//            trigger.setRepeatInterval(2000);
//            JobDataMap map = new JobDataMap();
//            map.putAsString("threadId", i);
//            trigger.setJobDataAsMap(map);
//            trigger.setJobDetail(jbDetail);

            alltriggers.add(trigger);
        }
        return alltriggers;
    }

    @Bean
    public SimpleTriggerFactoryBean firstTrigger() {
        SimpleTriggerFactoryBean triggerFactory = new SimpleTriggerFactoryBean();
        triggerFactory.setStartDelay(3000);
        triggerFactory.setRepeatInterval(1000);
        JobDataMap map = new JobDataMap();
        map.putAsString("threadId", 33333);
        triggerFactory.setJobDataAsMap(map);
        triggerFactory.setJobDetail(jobFactoryBean().getObject());
        return triggerFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean secondTrigger() {
        SimpleTriggerFactoryBean triggerFactory = new SimpleTriggerFactoryBean();
        triggerFactory.setStartDelay(1000);
        triggerFactory.setRepeatInterval(2000);
        JobDataMap map = new JobDataMap();
        map.putAsString("threadId", 11111);
        triggerFactory.setJobDataAsMap(map);
        triggerFactory.setJobDetail(jobFactoryBean().getObject());
        return triggerFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean thirdTrigger() {
        SimpleTriggerFactoryBean triggerFactory = new SimpleTriggerFactoryBean();
        triggerFactory.setStartDelay(1500);
        triggerFactory.setRepeatInterval(4000);
        JobDataMap map = new JobDataMap();
        map.putAsString("threadId", 999);
        triggerFactory.setJobDataAsMap(map);
        triggerFactory.setJobDetail(complextJobFactoryBean().getObject());
        return triggerFactory;
    }

    @Bean
    public JobDetailFactoryBean jobFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SimpleWorker.class);
        bean.setDurability(true);
        JobDataMap map = new JobDataMap();
        map.put("name", "parallelTestName");
        bean.setJobDataAsMap(map);
        return bean;
    }

    @Bean
    public JobDetailFactoryBean complextJobFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(ComplexWorker.class);
        bean.setDurability(true);
        JobDataMap map = new JobDataMap();
        map.put("name", "very complicated Job");
        bean.setJobDataAsMap(map);
        return bean;
    }

}

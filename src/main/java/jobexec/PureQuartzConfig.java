package jobexec;

import jobexec.worker.SimpleWorker;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMO on 18.12.2014.
 */
@Configuration
public class PureQuartzConfig {

    @Bean
    public SchedulerFactoryBean parallelSchedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setBeanName("parallelSchedulerFactoryBean");
        bean.setTriggers(simpleTriggers());
        return bean;
    }

    @Bean
    public SimpleTrigger[] simpleTriggers() {
        List<SimpleTrigger> alltriggers = new ArrayList<SimpleTrigger>();

        for (int i = 0; i < 10; i++) {
            SimpleTriggerFactoryBean triggerFactory = new SimpleTriggerFactoryBean();
            triggerFactory.setStartDelay(1000);
            triggerFactory.setRepeatInterval(2000);
            JobDataMap map = new JobDataMap();
            map.putAsString("threadId", i);
            triggerFactory.setJobDataAsMap(map);
            triggerFactory.setJobDetail(jobFactoryBean().getObject());
            SimpleTrigger trigger = triggerFactory.getObject();
            alltriggers.add(trigger);

        }
        SimpleTrigger[] arr = alltriggers.toArray(new SimpleTrigger[alltriggers.size()]);
        return arr;
    }

    @Bean
    public JobDetailFactoryBean jobFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(SimpleWorker.class);
        JobDataMap map = new JobDataMap();
        map.put("name", "parallelTestName");
        bean.setJobDataAsMap(map);
        return bean;
    }
}

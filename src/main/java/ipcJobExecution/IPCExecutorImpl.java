package ipcJobExecution;

import ipcJobExecution.helper.DefaultThreadIdGenerator;
import ipcJobExecution.helper.MultiThreadedServiceState;
import ipcJobExecution.helper.MultiThreadedServiceStateImpl;
import ipcJobExecution.helper.ThreadIdGenerator;
import jobexec.worker.SimpleWorker;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by MMO on 16.12.2014.
 */
public class IPCExecutorImpl {

    private Class<? extends Job> workerclass;
    private String methodToCall;

    private String serviceName;

    private int parallelThreads = 0;

    private ThreadIdGenerator idGenerator;

    private JobDataMap jobDataMaps;

    private String cronString;
    private int startDayInSeconds;
    private int pauseTimeInSeconds;

    private String groupName;
    private Date startTime;

    private Scheduler scheduler;
    private String id;

    private MultiThreadedServiceState serviceState;

    public IPCExecutorImpl(final IPCExecutorBuilder ipcExecutorBuilder) {
        this.workerclass = ipcExecutorBuilder.workerclass;
        this.cronString = ipcExecutorBuilder.cronString;
        this.startDayInSeconds = ipcExecutorBuilder.startDayInSeconds;
        this.parallelThreads = ipcExecutorBuilder.parallelThreads;
        this.pauseTimeInSeconds = ipcExecutorBuilder.pauseTimeInSeconds;
        this.startTime = ipcExecutorBuilder.startTime;
        this.methodToCall = ipcExecutorBuilder.methodToCall;
        this.idGenerator = ipcExecutorBuilder.idGenerator;
        this.groupName = ipcExecutorBuilder.groupName;
        this.serviceName = ipcExecutorBuilder.serviceName;

        serviceState = new MultiThreadedServiceStateImpl();

    }

    public void buildExecutor() throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        scheduler = sf.getScheduler();
        if (idGenerator == null) {
            idGenerator = new DefaultThreadIdGenerator();
        }
        for (int i = 0; i < parallelThreads; i++) {
            String nextId = idGenerator.getNextId();

            if (startTime == null) {
                startTime = new Date();
            }

            ScheduleBuilder schedulBuilder = getScheduleBuilder();

            Trigger trigger = getTrigger(nextId, schedulBuilder);

            JobKey jobKey = new JobKey("Job_" + nextId, groupName);

            JobDetail jobDetail = getJobDetail(jobKey);

            //            JobDataMap jobdatamap = new JobDataMap();
            //            jobdatamap.put("threadId", nextId);

            scheduler.scheduleJob(jobDetail, trigger);

        }
    }

    private JobDetail getJobDetail(final JobKey jobKey) {
        return JobBuilder.newJob(SimpleWorker.class)
                         .withIdentity(jobKey).build();
    }

    private ScheduleBuilder getScheduleBuilder() {
        ScheduleBuilder schedulBuilder;
        if (cronString == null) {
            schedulBuilder = simpleSchedule()
                    .withMisfireHandlingInstructionNextWithExistingCount()
                    .withIntervalInSeconds(pauseTimeInSeconds)
                    .repeatForever();
        } else {
            schedulBuilder = CronScheduleBuilder.cronSchedule(cronString);
        }
        return schedulBuilder;
    }

    private Trigger getTrigger(final String nextId, final ScheduleBuilder schedulBuilder) {
        Trigger trigger = newTrigger()
                .withIdentity("Trigger_" + groupName + nextId, groupName)
                .startAt(startTime)
                .withSchedule(schedulBuilder)
                .usingJobData("threadId", nextId)
                .build();

        return trigger;
    }

    public void start() throws SchedulerException {
        scheduler.start();
    }

    public void stop() {
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void reset() {

    }

    public static class IPCExecutorBuilder {

        private Class<? extends Job> workerclass;
        private String methodToCall;
        private String serviceName;

        private int parallelThreads = 1;

        private ThreadIdGenerator idGenerator;

        private JobDataMap jobDataMaps;

        private String cronString;
        private int startDayInSeconds;
        private int pauseTimeInSeconds;

        private String groupName;
        private Date startTime;

        private Scheduler scheduler;
        private String id;

        public IPCExecutorBuilder setWorkerclass(final Class<? extends Job> workerclass) {
            this.workerclass = workerclass;
            return this;
        }

        public IPCExecutorBuilder setMethodToCall(final String methodToCall) {
            this.methodToCall = methodToCall;
            return this;
        }

        public IPCExecutorBuilder setParallelThreads(final int parallelThreads) {
            this.parallelThreads = parallelThreads;
            return this;
        }

        public IPCExecutorBuilder setIdGenerator(final ThreadIdGenerator idGenerator) {
            this.idGenerator = idGenerator;
            return this;
        }

        public IPCExecutorBuilder setJobDataMaps(final JobDataMap jobDataMaps) {
            this.jobDataMaps = jobDataMaps;
            return this;
        }

        public IPCExecutorBuilder setCronString(final String cronString) {
            this.cronString = cronString;
            return this;
        }

        public IPCExecutorBuilder setStartDayInSeconds(final int startDayInSeconds) {
            this.startDayInSeconds = startDayInSeconds;
            return this;
        }

        public IPCExecutorBuilder setPauseTimeInSeconds(final int pauseTimeInSeconds) {
            this.pauseTimeInSeconds = pauseTimeInSeconds;
            return this;
        }

        public IPCExecutorBuilder setGroupName(final String groupName) {
            this.groupName = groupName;
            return this;
        }

        public IPCExecutorBuilder setStartTime(final Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public IPCExecutorBuilder setScheduler(final Scheduler scheduler) {
            this.scheduler = scheduler;
            return this;
        }

        public IPCExecutorBuilder setId(final String id) {
            this.id = id;
            return this;
        }

        public IPCExecutorBuilder setServiceName(final String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public IPCExecutorImpl build() {
            IPCExecutorImpl ret = new IPCExecutorImpl(this);
            try {
                ret.buildExecutor();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }

            return ret;
        }

    }
}
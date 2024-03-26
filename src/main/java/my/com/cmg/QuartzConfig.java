package my.com.cmg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import my.com.cmg.iwp.integration.schedule.CdrJob;
@Configuration
public class QuartzConfig {
	
	 @Bean
	    public JobDetailFactoryBean cdrJob() {
	        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
	        factoryBean.setJobClass(CdrJob.class); // Set your job class here
	        // Configure other properties of the job detail if needed
	        return factoryBean;
	    }

	    @Bean(name = "cdrJobTrigger")
	    public CronTriggerFactoryBean cdrJobTrigger(JobDetailFactoryBean cdrJob) {
	        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
	        triggerFactoryBean.setJobDetail(cdrJob.getObject());
	        triggerFactoryBean.setCronExpression("0 0/22 * 1/1 * ? *"); // Your cron expression
	        return triggerFactoryBean;
	    }
	    
	    @Bean
	    public SchedulerFactoryBean quartzScheduler() {
	        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
	        // You can configure scheduler properties here if needed
	        return schedulerFactoryBean;
	    }

}

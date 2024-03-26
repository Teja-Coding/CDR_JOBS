package my.com.cmg.iwp.integration;

import java.text.ParseException;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import my.com.cmg.iwp.maintenance.model.HqConfig;
import my.com.cmg.iwp.maintenance.service.HQConfigService;

@Controller
public class IntegrationRestartRest {
	
	private static final int CDR_JOB_TYPE = 9;
	
	@Autowired
	@Qualifier("cdrJobTrigger")
	private CronTriggerFactoryBean cdrJobTrigger;
	
	@Autowired
	private SchedulerFactoryBean quartzScheduler;
	
	@Autowired
	private HQConfigService hqConfigService;
	
	@RequestMapping(value = "/internalsch/restart", method = RequestMethod.GET)
	public @ResponseBody String restartJobs() {
		try {
			getHqConfigService().getHQConfig();
			HqConfig hqConfig = getHqConfigService().getHQConfig();
		
			reschedule(getCdrJobTrigger(), getCronExpression(hqConfig, CDR_JOB_TYPE));
			
			return "success";
		} catch (ParseException e) {
			e.printStackTrace();
			return "failed" + e.getMessage();
		} catch (SchedulerException e) {
			e.printStackTrace();
			return "failed" + e.getMessage();
		}
	}
	
	
	public void reschedule(final CronTriggerFactoryBean trigger, final String cronExpression) throws ParseException,
	SchedulerException {
		Scheduler scheduler = this.quartzScheduler.getObject();
		trigger.setCronExpression(cronExpression);
		scheduler.rescheduleJob(trigger.getObject().getKey(), trigger.getObject());
		scheduler.start();
//		scheduler.rescheduleJob(trigger.getName(),
//				trigger.getGroup(), trigger);
	}
	
	public String getCronExpression(final HqConfig hqConfig, final int jobType) {
		String cronExpresion = "0 0/";
		String cronExpressionSurffix = " * * * ?";
		switch (jobType) {
		/*
		 * case SPUB_JOB_TYPE: cronExpresion += hqConfig.getIntervalSpub().intValue();
		 * break; case SDR_JOB_TYPE: cronExpresion +=
		 * hqConfig.getIntervalKpk().intValue(); break; case INDENT_JOB_TYPE:
		 * cronExpresion += hqConfig.getIntervalExtindent().intValue(); break; case
		 * ISSUE_JOB_TYPE: cronExpresion += hqConfig.getIntervalExtissue().intValue();
		 * break; case RP_JOB_TYPE: cronExpresion +=
		 * hqConfig.getIntervalExtrp().intValue(); break;
		 */
		case CDR_JOB_TYPE:
			cronExpresion += hqConfig.getIntervalExtcdr().intValue();
			break;
		/*
		 * case PN_JOB_TYPE: cronExpresion += hqConfig.getIntervalExtpn().intValue();
		 * break; case PENALTY_JOB_TYPE: cronExpresion +=
		 * hqConfig.getIntervalPenalty().intValue(); break; case SPUB_COR_JOB_TYPE:
		 * cronExpresion += hqConfig.getIntervalSpubCor().intValue(); break;
		 */
		}
		cronExpresion += cronExpressionSurffix;
		return cronExpresion;
	}
	
	public CronTriggerFactoryBean getCdrJobTrigger() {
		return cdrJobTrigger;
	}
	
	public HQConfigService getHqConfigService() {
		return hqConfigService;
	}
	
}

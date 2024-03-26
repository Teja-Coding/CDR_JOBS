/*
 * package my.com.cmg.iwp.integration;
 * 
 * import java.sql.SQLException; import java.text.ParseException;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.quartz.Scheduler; import org.quartz.SchedulerException; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Qualifier; import
 * org.springframework.context.ApplicationListener; import
 * org.springframework.context.event.ContextRefreshedEvent; import
 * org.springframework.scheduling.quartz.CronTriggerFactoryBean; import
 * org.springframework.scheduling.quartz.SchedulerFactoryBean; import
 * org.springframework.stereotype.Component; import org.zkoss.spring.SpringUtil;
 * 
 * import my.com.cmg.iwp.backend.util.CommonUtilities; import
 * my.com.cmg.iwp.maintenance.model.HqConfig; import
 * my.com.cmg.iwp.maintenance.service.HQConfigService; import
 * my.com.cmg.iwp.util.MyApplicationContextAware;
 * 
 * @Component public class AppInitialListener implements
 * ApplicationListener<ContextRefreshedEvent> {
 * 
 * @Autowired private SchedulerFactoryBean quartzScheduler;
 * 
 * @Autowired
 * 
 * @Qualifier("cdrJobTrigger") private CronTriggerFactoryBean cdrJobTrigger;
 * 
 * @Autowired private HQConfigService hqConfigService;
 * 
 * @Autowired private DataSource dataSource;
 * 
 * private static final int CDR_JOB_TYPE = 9;
 * 
 * @Override public void onApplicationEvent(final ContextRefreshedEvent arg0) {
 * try { getHqConfigService().getHQConfig(); HqConfig hqConfig =
 * getHqConfigService().getHQConfig();
 * 
 * reschedule(getCdrJobTrigger(), getCronExpression(hqConfig, CDR_JOB_TYPE));
 * 
 * MyApplicationContextAware.setMessageMap(CommonUtilities.loadMessageMap(
 * getDataSource().getConnection())); CommonUtilities.loadRefCodesMap(); } catch
 * (ParseException e) { e.printStackTrace(); } catch (SchedulerException e) {
 * e.printStackTrace(); } catch (SQLException e) { e.printStackTrace(); } }
 * 
 * public String getCronExpression(final HqConfig hqConfig, final int jobType) {
 * String cronExpresion = "0/"; String cronExpressionSurffix = " * * * *";
 * switch (jobType) {
 * 
 * case CDR_JOB_TYPE: cronExpresion += hqConfig.getIntervalExtcdr().intValue();
 * break;
 * 
 * } cronExpresion += cronExpressionSurffix; return cronExpresion; }
 * 
 * public void reschedule(final CronTriggerFactoryBean trigger, final String
 * cronExpression) throws ParseException, SchedulerException { Scheduler
 * scheduler = this.getQuartzScheduler().getScheduler();
 * trigger.setCronExpression(cronExpression);
 * scheduler.rescheduleJob(trigger.getObject().getKey(), trigger.getObject());
 * //scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(),trigger);
 * 
 * }
 * 
 * public SchedulerFactoryBean getQuartzScheduler() { return
 * this.quartzScheduler; }
 * 
 * public void setQuartzScheduler(final SchedulerFactoryBean quartzScheduler) {
 * this.quartzScheduler = quartzScheduler; }
 * 
 * public HQConfigService getHqConfigService() { //
 * //setHqConfigService((HQConfigService) SpringUtil.getBean("hqConfigService");
 * return this.hqConfigService; }
 * 
 * public CronTriggerFactoryBean getCdrJobTrigger() { return this.cdrJobTrigger;
 * }
 * 
 * public DataSource getDataSource() { return dataSource; }
 * 
 * }
 */
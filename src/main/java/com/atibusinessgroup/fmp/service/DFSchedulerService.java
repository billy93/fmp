package com.atibusinessgroup.fmp.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.DataFeedScheduler;
import com.atibusinessgroup.fmp.repository.DFSchedulerRepository;
import com.atibusinessgroup.fmp.scheduler.DFAutomaticJob;
import com.atibusinessgroup.fmp.scheduler.DFManualJob;

@Service
public class DFSchedulerService {

	private final Logger log = LoggerFactory.getLogger(DFSchedulerService.class);
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	private final DFSchedulerRepository dfSchedulerRepository;
	
	public DFSchedulerService(DFSchedulerRepository dfSchedulerRepository) {
		this.dfSchedulerRepository = dfSchedulerRepository;
	}
	
	public DataFeedScheduler save(DataFeedScheduler dataFeedScheduler) {
		log.debug("Request to save DataFeedScheduler : {}", dataFeedScheduler);
        return dfSchedulerRepository.save(dataFeedScheduler);
	}

	public Page<DataFeedScheduler> findAll(Pageable pageable) {
		log.debug("Request to get all DataFeedSchedulers");
        return dfSchedulerRepository.findAll(pageable);
	}

	public DataFeedScheduler findOne(String id) {
		log.debug("Request to get DataFeedScheduler : {}", id);
        return dfSchedulerRepository.findOne(id);
	}

	public void delete(String id) {
		log.debug("Request to delete DataFeedScheduler : {}", id);
		dfSchedulerRepository.delete(id);
	}

	public DataFeedScheduler findDfScheduler(String type) {
		log.debug("Request to get DataFeedScheduler : {}", type);
		Query query = new Query();
		query.addCriteria(Criteria.where("type").is(type));
		
		return mongoTemplate.findOne(query, DataFeedScheduler.class);
	}
	
	public void startAllScheduler() {
		startDFManualScheduler();
		startDFAutomaticScheduler();
	}
	
	public void startDFManualScheduler() {
		try {
			DataFeedScheduler dfManual = findDfScheduler("manual");
			JobDetail dfManualJobDetail = JobBuilder.newJob(DFManualJob.class).withIdentity("DFManualJob", "DF").build();
			dfManualJobDetail.getJobDataMap().put("StartDateYear", dfManual.getStartTime().getYear());
			dfManualJobDetail.getJobDataMap().put("StartDateMonth", dfManual.getStartTime().getMonthValue());
			dfManualJobDetail.getJobDataMap().put("StartDateDay", dfManual.getStartTime().getDayOfMonth());
			dfManualJobDetail.getJobDataMap().put("EndDateYear", dfManual.getEndDate().getYear());
			dfManualJobDetail.getJobDataMap().put("EndDateMonth", dfManual.getEndDate().getMonthValue());
			dfManualJobDetail.getJobDataMap().put("EndDateDay", dfManual.getEndDate().getDayOfMonth());
			dfManualJobDetail.getJobDataMap().put("Filepath", dfManual.getFilepath());
			
			Trigger dfManualTrigger = TriggerBuilder.newTrigger()
					.withIdentity("DFManualTrigger", "DF")
					.withSchedule(CronScheduleBuilder.cronSchedule(createCronTemplateByTime(dfManual.getStartTime())))
					.build();
			Scheduler dfManualScheduler = new StdSchedulerFactory().getScheduler();
			dfManualScheduler.start();
			dfManualScheduler.scheduleJob(dfManualJobDetail, dfManualTrigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startDFAutomaticScheduler() {
		try {
			DataFeedScheduler dfAutomatic = findDfScheduler("automatic");
			JobDetail dfAutomaticJobDetail = JobBuilder.newJob(DFAutomaticJob.class).withIdentity("DFAutomaticJob", "DF").build();
			dfAutomaticJobDetail.getJobDataMap().put("Filepath", dfAutomatic.getFilepath());
			
			Trigger dfAutomaticTrigger = TriggerBuilder.newTrigger()
					.withIdentity("DFAutomaticTrigger", "DF")
					.withSchedule(CronScheduleBuilder.cronSchedule(createCronTemplateByTimeAndDayOfWeek(dfAutomatic.getStartTime(), dfAutomatic.getDayOfWeek())))
					.build();
			Scheduler dfAutomaticScheduler = new StdSchedulerFactory().getScheduler();
			dfAutomaticScheduler.start();
			dfAutomaticScheduler.scheduleJob(dfAutomaticJobDetail, dfAutomaticTrigger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateDFManualScheduler(DataFeedScheduler df) {
		try {
			Scheduler dfManualScheduler = new StdSchedulerFactory().getScheduler();
			JobKey jobKey = findJobKey("DFManualJob");
			List<? extends Trigger> triggers = dfManualScheduler.getTriggersOfJob(jobKey);
			if(triggers.size() > 0) {
				TriggerBuilder tb = null;
				tb = triggers.get(0).getTriggerBuilder();
				Trigger newTrigger = tb.withSchedule(CronScheduleBuilder.cronSchedule(createCronTemplateByTime(df.getStartTime()))).build();
				dfManualScheduler.rescheduleJob(triggers.get(0).getKey(), newTrigger);
				
				JobDetail oldJobDetail = dfManualScheduler.getJobDetail(jobKey);
				if(Objects.nonNull(oldJobDetail)) {
					JobDataMap jobDataMap = oldJobDetail.getJobDataMap();
					jobDataMap.put("StartDateYear", df.getStartTime().getYear());
					jobDataMap.put("StartDateMonth", df.getStartTime().getMonthValue());
					jobDataMap.put("StartDateDay", df.getStartTime().getDayOfMonth());
					jobDataMap.put("EndDateYear", df.getEndDate().getYear());
					jobDataMap.put("EndDateMonth", df.getEndDate().getMonthValue());
					jobDataMap.put("EndDateDay", df.getEndDate().getDayOfMonth());
					jobDataMap.put("Filepath", df.getFilepath());
					JobBuilder jb = oldJobDetail.getJobBuilder();
					JobDetail newJobDetail = jb.usingJobData(jobDataMap).storeDurably().build();
					dfManualScheduler.addJob(newJobDetail, true);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateDFAutomaticScheduler(DataFeedScheduler df) {
		try {
			Scheduler dfAutomaticScheduler = new StdSchedulerFactory().getScheduler();
			JobKey jobKey = findJobKey("DFAutomaticJob");
			List<? extends Trigger> triggers = dfAutomaticScheduler.getTriggersOfJob(jobKey);
			if(triggers.size() > 0) {
				TriggerBuilder tb = null;
				tb = triggers.get(0).getTriggerBuilder();
				Trigger newTrigger = tb.withSchedule(CronScheduleBuilder.cronSchedule(createCronTemplateByTimeAndDayOfWeek(df.getStartTime(), df.getDayOfWeek()))).build();
				dfAutomaticScheduler.rescheduleJob(triggers.get(0).getKey(), newTrigger);
				
				JobDetail oldJobDetail = dfAutomaticScheduler.getJobDetail(jobKey);
				if(Objects.nonNull(oldJobDetail)) {
					JobDataMap jobDataMap = oldJobDetail.getJobDataMap();
					jobDataMap.put("Filepath", df.getFilepath());
					JobBuilder jb = oldJobDetail.getJobBuilder();
					JobDetail newJobDetail = jb.usingJobData(jobDataMap).storeDurably().build();
					dfAutomaticScheduler.addJob(newJobDetail, true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopAllScheduler() {
		stopDFManualScheduler();
		stopDFAutomaticScheduler();
	}
	
	public void stopDFManualScheduler() {
		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.deleteJob(findJobKey("DFManualJob"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopDFAutomaticScheduler() {
		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.deleteJob(findJobKey("DFAutomaticJob"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JobKey findJobKey(String jobName) {
		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		    // Check running jobs first
		    for (JobExecutionContext runningJob : scheduler.getCurrentlyExecutingJobs()) {
		        if (Objects.equals(jobName, runningJob.getJobDetail().getKey().getName())) {
		            return runningJob.getJobDetail().getKey();
		        }
		    }
		    // Check all jobs if not found
		    for (String groupName : scheduler.getJobGroupNames()) {
		        for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
		            if (Objects.equals(jobName, jobKey.getName())) {
		                return jobKey;
		            }
		        }
		    }
		    
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String createCronTemplateByTime(ZonedDateTime startTime) {
		String result = null;
		if(startTime != null) 
			result = "0 " + startTime.getMinute() + " " + startTime.getHour() +" * * ?";
		
		return result;
	}
	
	public String createCronTemplateByTimeAndDayOfWeek(ZonedDateTime startTime, String dayOfWeek) {
		String result = null;
		if(startTime != null) 
			result = "0 " + startTime.getMinute() + " " + startTime.getHour() +" ? * "+ dayOfWeek;
		
		return result;
	}
	
}

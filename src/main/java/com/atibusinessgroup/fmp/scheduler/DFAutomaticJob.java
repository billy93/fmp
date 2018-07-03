package com.atibusinessgroup.fmp.scheduler;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

@Service
public class DFAutomaticJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String filePath = dataMap.getString("Filepath");
		Date currentDate = new Date();
		System.out.println("Hari sesuai, Job DFAutomatic dijalankan.");
		System.out.println("Filepath "+filePath);
		System.out.println("Today "+currentDate);
	}

}

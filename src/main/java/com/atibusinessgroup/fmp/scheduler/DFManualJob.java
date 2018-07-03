package com.atibusinessgroup.fmp.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DFManualJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		int startDateYear = dataMap.getInt("StartDateYear");
		int startDateMonth = dataMap.getInt("StartDateMonth");
		int startDateDay = dataMap.getInt("StartDateDay");
		String startDateStr = startDateYear+"-"+startDateMonth+"-"+startDateDay;
		
		int endDateYear = dataMap.getInt("EndDateYear");
		int endDateMonth = dataMap.getInt("EndDateMonth");
		int endDateDay = dataMap.getInt("EndDateDay");
		String endDateStr = endDateYear+"-"+endDateMonth+"-"+endDateDay;
		
		String filePath = dataMap.getString("Filepath");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse(startDateStr);
			Date endDate = sdf.parse(endDateStr);
			Date currentDate = new Date();
			if((startDate.compareTo(currentDate) == 0 || startDate.compareTo(currentDate) < 0)
					&& (endDate.compareTo(currentDate) == 0 || endDate.compareTo(currentDate) > 0)) {
				System.out.println("Tanggal sesuai, Job DFManual dijalankan.");
				System.out.println("Filepath "+filePath);
				System.out.println("Startdate "+startDate);
				System.out.println("EndDate "+endDate);
				System.out.println("Today "+currentDate);
			} else {
				System.out.println("Tanggal belum sesuai, Job DFManual tidak dijalankan.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

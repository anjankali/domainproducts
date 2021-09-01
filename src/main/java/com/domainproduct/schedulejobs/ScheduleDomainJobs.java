package com.domainproduct.schedulejobs;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDomainJobs {

	private static final Logger log = LoggerFactory.getLogger(ScheduleDomainJobs.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	DomainJobService jobService;
	
	@Scheduled(cron = "0   0   8   *    *    *")
	public void scheduleConfiguredJobs() {
		log.info("Job Scheduling started at {}", dateFormat.format(new Date()));
		jobService.processScheduleJob();
		log.info("Job Scheduling ended at {}", dateFormat.format(new Date()));
	}
	
	
	@Scheduled(cron = "0   0   10   *    *    *")
	public void scheduleFailedJobs() {
		log.info("Job Scheduling started at {}", dateFormat.format(new Date()));
		jobService.processFailedScheduleJob();
		log.info("Job Scheduling ended at {}", dateFormat.format(new Date()));
	}
}

/*
Write a cron expression that executes a task everyday at 8 AM
0   0   8   *    *    *
*/
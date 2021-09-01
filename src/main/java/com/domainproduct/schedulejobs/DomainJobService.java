package com.domainproduct.schedulejobs;

public interface DomainJobService {
	void processScheduleJob();
	
	void processFailedScheduleJob();
}

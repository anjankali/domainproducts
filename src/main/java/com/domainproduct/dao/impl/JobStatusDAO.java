package com.domainproduct.dao.impl;

import java.sql.Date;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JobStatusDAO extends JdbcDaoSupport {

private static final Logger log = LoggerFactory.getLogger(JobConfigDao.class);
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
/*
	This method used to save the Job Status Details and status of the Job Notification about the Job with Customer basis
 */
	public void updateCustomerEmailStatus(int domainTypeId, int ruleId, long srartDate, long endDate, boolean status) {
		//Save details to job_status <table> with status field
		//status=0 - Sucess
		//status=1 - InProgress
		//status=-1 - Failed
	}

}

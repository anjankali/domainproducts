package com.domainproduct.schedulejobs;

import java.sql.Date;

public class DoaminScheduleHelper {
	
	public String getDateDurationBasedonRuleAction(Date startDate,int applicalbeDays,int durationInMonths, String rule, String action) {
		long startDateinLong  = 0;//Enhance the code to get the start date in termes of long
		long durationMonths  = 0;// To modify the DurationMonths data to get the details in long
		long applicableDays = 0;//applicalbeDays in long need to convert
		long totalTimeinLong = startDateinLong + durationMonths;
	
		if(rule.equals("BEOFRE")) {
			totalTimeinLong = totalTimeinLong - applicalbeDays;
		}
		if(rule.equals("AFTER")) {
			totalTimeinLong = totalTimeinLong + applicalbeDays;
		}
		
		String baseQuery = "";
		if(action.equals("expiration")) {
			//Prepare Expireation logic
			baseQuery = "startdate btween " +totalTimeinLong;
		}
		
		if(action.equals("activation")) {
			//prepare Activation logic
			baseQuery = "startdate betwen  " +totalTimeinLong;
		}
		
		return baseQuery;
	}
}

package com.domainproduct.schedulejobs;

public class ConfigJobs {
	private String domainTypeId;
	private String ruleId;
	private String domain;
	private String rulecAction;
	private int applicableDays;
	
	public String getDomainTypeId() {
		return domainTypeId;
	}
	public void setDomainTypeId(String domainTypeId) {
		this.domainTypeId = domainTypeId;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getRulecAction() {
		return rulecAction;
	}
	public void setRulecAction(String rulecAction) {
		this.rulecAction = rulecAction;
	}
	public int getApplicableDays() {
		return applicableDays;
	}
	public void setApplicableDays(int applicableDays) {
		this.applicableDays = applicableDays;
	}
	
	
}



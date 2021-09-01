package com.domainproduct.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.domainproduct.model.Product;
import com.domainproduct.schedulejobs.ConfigJobs;

public class JobConfigDao extends JdbcDaoSupport {

private static final Logger log = LoggerFactory.getLogger(JobConfigDao.class);
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	//This method will return the all Configured jobs based on the sattus
	//jobStatus = 0, all configured jobs
	//jobStatus = -1 failed jobs
	public List<ConfigJobs> getAllConfiguredJobs(int jobStatus){
		String sql = "SELECT jc.domaintype_id, jc.rule_id,dt.domain,jr.rule_action FROM job_config jc "
				+ "join domain_types dt on jc.domaintype_id = dt.domaintype_id"
				+ "join job_rules jr on jc.rule_id = jr.rule_id"
				+ " where jc.active= 1"
				+ " order by jc.domaintype_id, jc.rule_id;"; //Pagination need to plan as like offset and 
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<ConfigJobs> result = new ArrayList<ConfigJobs>();
		for(Map<String, Object> row:rows){
			ConfigJobs cjob = new ConfigJobs();
			cjob.setDomainTypeId((String)row.get("domaintype_id"));
			cjob.setRuleId((String)row.get("rule_id"));
			cjob.setDomain((String)row.get("domain"));
			cjob.setRulecAction((String)row.get("rule_action"));
			 
			result.add(cjob);
		}
		log.info("ProductDAOImpl:getAllConfiguredJobs"); //Need to add detailed data logs
		return result;
	}
	
	
	//Pagination need to plan as like offset and 
	//This method will prepare the Data set for Product Customer Email details based on the rule and Action
	public List<Product> getProductEmailsByDoaminRule(String domainTypeId, String rule, String action){
		
		String sql = " select p.* from Product p join with domain table + job rules tables + based on the BEFORE and AFTER/applicable days duratoin then prepared the query"
				+ " where p.doamintype_id  "; 
		
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);//.set(1, jobStatus);
		
		List<Product> result = new ArrayList<Product>();
		for(Map<String, Object> row:rows){
			Product product = new Product();
			product.setCustomerId((String)row.get("customer_id"));
			product.setProductName((String)row.get("product_name"));
			product.setDomain((String)row.get("domaintype_id"));
			product.setStartDate((String)row.get("start_date"));
			product.setDurationMonths((int)row.get("duration_months"));
			 
			result.add(product);
		}
		log.info("ProductDAOImpl:getProductEmailsByDoaminRule"); //Need to add detailed data logs
		return result;
	}
	
}

package com.domainproduct.schedulejobs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domainproduct.dao.impl.JobConfigDao;
import com.domainproduct.dao.impl.JobStatusDAO;
import com.domainproduct.model.Product;
import com.domainproducts.utils.EmailUtil;

@Service
public class DomainJobServiceImpl implements DomainJobService{

	private static final Logger log = LoggerFactory.getLogger(DomainJobServiceImpl.class);
	
	@Autowired
	JobConfigDao jobConfigDao;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	JobStatusDAO jobStatusDao;

	//This method will process the configed jobs from the scheduler
	@Override
	public void processScheduleJob() {
		List<ConfigJobs> scheduledJobs =jobConfigDao.getAllConfiguredJobs(1);
		for(ConfigJobs eachJob:scheduledJobs) {
			processDomainJob(eachJob);
		}
	}

	//This method will process the if configed jobs are failed and called from the scheduler
	@Override
	public void processFailedScheduleJob() {
		List<ConfigJobs> scheduledJobs =jobConfigDao.getAllConfiguredJobs(-1);
		for(ConfigJobs eachJob:scheduledJobs) {
			processDomainJob(eachJob);
		}
	}
	
	//common method is used to save the succes and failed jobs status
	//When Job running status 1 will update, once sucess the same satus is 0, in case of failure -1 will be updates
	private void processDomainJob(ConfigJobs jobConfig) {
		String domain = jobConfig.getDomain();
		String ruleAction = jobConfig.getRulecAction();
		String ruleId = jobConfig.getRuleId();
		String domainTypeId = jobConfig.getDomainTypeId();
		
		String[] ruleWithAction = ruleAction.split(":");
		String rule = ruleWithAction[0];
		String action = ruleWithAction[1];
		
		List<Product> productDetails = jobConfigDao.getProductEmailsByDoaminRule(domainTypeId, rule, action);
		for(Product product:productDetails) {
			long start = System. currentTimeMillis();
			boolean emailStatus = emailUtil.processEmailforDomainProductCustomer(product.getDomain(), "Email Content");
			long end = System. currentTimeMillis();
			
			
			if(!emailStatus) {
				log.info("Email Notification Failed to the customer and Doamin Job"+product.toString());
			}
			jobStatusDao.updateCustomerEmailStatus(Integer.parseInt(domainTypeId), Integer.parseInt(ruleId), start, end, emailStatus);
		}
		
	}
}

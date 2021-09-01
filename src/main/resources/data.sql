
/*
To Store the Product Details
*/
CREATE TABLE product (
  product_id int NOT NULL AUTO_INCREMENT,
  customer_id VARCHAR(10) NOT NULL,
  product_name VARCHAR(100) NOT NULL,
  domaintype_id VARCHAR(40) NOT NULL, -- FK to domain_type <table>
  start_date datetime NOT NULL,
  duration_months int NOT NULL,
  PRIMARY KEY (product_id)
  -- FOREIGN KEY (`domaintype_id`) REFERENCES `domain_types`(`domaintype_id`)
);


--Domain types and configuation Details
CREATE TABLE domain_types (
  domaintype_id int NOT NULL AUTO_INCREMENT,
  domain VARCHAR(40) NOT NULL, 
  active int NOT NULL,   -- 0: inactive, 1- active
  PRIMARY KEY (domaintype_id)
);
--Example
--domaintype_id, domain, active
--1				 Domain		1
--2				 Hosting    1
--3				 PDomain    0


--Job Configuration table and make job as active or in-active
-- per Doamin only uniqe rule will be applicable, to avoid duplicates
CREATE TABLE job_config (
  domaintype_id int,
  rule_id  int,
  active int NOT NULL   -- 0: inactive, 1- active
  PRIMARY KEY (domaintype_id, rule_id)
  --FOREIGN KEY (`domaintype_id`) REFERENCES `domain_types`(`domaintype_id`)
  --FOREIGN KEY (`rule_id`) REFERENCES `job_rules`(`rule_id`)
);
-- Example
-- domaintype_id,   rule_id,  active
-- 2			    2			1
-- 3				1			1


-- Job Rules table for defining new rules and days combination
CREATE TABLE job_rules (
  rule_id int NOT NULL AUTO_INCREMENT,
  rule_action VARCHAR(10) NOT NULL, -- BEFORE/AFTER
  applicable_days int NOT NULL
  PRIMARY KEY (rule_id, rule_action)
);
-- Example
-- rule_id,   rule_action,  			applicable_days
-- 1			   BEFORE:expiration			2
-- 2 			   AFTER:activation             1
-- 3	           AFTER :expiration            3
-- 4			   BEFORE:expiration            9
-- 5			   BEFORE:expiration            2


-- Job status will be logged to this table to track and re-schedule the jobs
CREATE TABLE job_status (
   domaintype_id int,
   rule_id int,
   start_time datetime,
   end_time datetime,
   status      -- 0:Sucess , 1: In-Progress, -1.Failed
);
--Example
--10, 2, 01-09-2021 12:00:00, 01-09-2021 12:05:00, 0 


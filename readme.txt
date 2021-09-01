This project is developed to store the Product and Domain Information based on the below requirements and assumptions

Requirements
---------------
1. DomainProducts CRUD operations
2. Need to support Three Domains (Domain, Hosting, and Protected Domain (“pdomain”)) and also can be extensible 
3. Email Notification with Transaction Supported
4. Configuration of the EMail Notifications
	a. Domain sends email 2 days before expiration.
	b. Hosting sends email 1 day after activation and 3 days before expiration.
	c. Protected Domain sends email 9 days before expiration and 2 days before expiration.
5. Creating the Customers with different platform based (RestAPI), Clients from Java/PostMan, Perl, Phython
6. Listing the Customers with date sorting
7. Deleting the Customers details with CsutomerId, ProductName, Domain


New Jobs will be added like below details in DB, with out changing the code
-------------------------------------------------------------------
1. In case of new rules, please insert  in job_rules <table>
2. In case of new Domain, please insert details in domain_types <table>
3. Configure new JOB in job_config <table> :system will pics this record and process based on the Rule/Action provided in above two tables


Assumptions
-------------------
1. Activation date = Product startDate considering
2. Expiration date = Product<startDate> + DurationInMonths
3. Customer Email details will be saved in Product <table> itself
4. Each Scheduling job requirement is independent suppose
	Hosting sends email 1 day after activation and 3 days before expiration.
	Job 1: Hosting Domain Email Notification 1 Day After Activation
	Job 2: Hosting Domain Email Notification 3 Day Before expiration
5. Email Functionality will provide the status of the Customer Emails status
6. Jobs will run Synchronously
	Application will support for Asyc Job Processing

Miscellaneous Cases
---------------------
1.Email Server Not be respond in case of certain time-out



Need to Enhance below requirements in Future Implementations
------------------------------------------------------------
1. Need to implement Role Based Authorization in case of deleting the Products
2. Need to provide Security to Access the Application
3. Reload the Config at runtime with-out restarting the server
4. Each job customer email what exact time was/s delivered need to capture
5. RestAPI based testing platform to develop
6. Synchronous/Asynchronous Job Processing
7. Ability to support the Application with multiple servers 
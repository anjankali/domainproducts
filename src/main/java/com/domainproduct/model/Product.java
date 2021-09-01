package com.domainproduct.model;

public class Product {

	private String CustomerId;
	private String ProductName;
	private String domain;
	private String startDate;
	private int durationMonths;
	
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getDurationMonths() {
		return durationMonths;
	}
	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}
	
	@Override
	public String toString() {
		return "Product [CustomerId=" + CustomerId + ", ProductName=" + ProductName + ", domain=" + domain
				+ ", startDate=" + startDate + ", durationMonths=" + durationMonths + "]";
	}

	
}
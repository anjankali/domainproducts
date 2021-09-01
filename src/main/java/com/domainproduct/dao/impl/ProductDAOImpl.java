package com.domainproduct.dao.impl;

import java.util.List;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.domainproduct.model.Product;

@Repository
public class ProductDAOImpl extends JdbcDaoSupport implements ProductDAO{
	
	@Autowired 
	DataSource dataSource;
	
	@PostConstruct
	private void initialize(){
		setDataSource(dataSource);
	}
	
	
	//customer_id,  product_name,  domaintype_id,  start_date, duration_months
	@Override
	public void insertProduct(Product emp) {
		String sql = "INSERT INTO product " +
				"(customer_id,  product_name,  domaintype_id,  start_date, duration_months) VALUES (?, ?, ?, ?, ?)" ;
		getJdbcTemplate().update(sql, new Object[]{emp.getCustomerId(), emp.getProductName(),emp.getDomain(),emp.getStartDate(),emp.getDurationMonths()});
	}
	
	@Override
	public void insertProducts(final List<Product> products) {
		String sql = "INSERT INTO product " + "(customer_id,  product_name,  domaintype_id,  start_date, duration_months) VALUES (?, ?, ?, ?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Product product = products.get(i); 
				ps.setString(1, product.getCustomerId());
				ps.setString(2, product.getProductName());
				ps.setString(2, product.getDomain());
				ps.setString(2, product.getStartDate());
				ps.setInt(2, product.getDurationMonths());
				
			}
			
			public int getBatchSize() {
				return products.size();
			}
		});

	}
	
	@Override
	public List<Product> getAllProducts(){
		String sql = "SELECT * FROM product order by start_date desc"; //Pagination need to plan as like offset and 
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
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
		
		return result;
	}
	
	
	@Override
	public boolean deleteProduct(String customerId, String productName, String domainTypeId) {
		String sql = "delete product where customer_id=? and product_name = ? and  domaintype_id = ?" ;
		getJdbcTemplate().update(sql, new Object[]{customerId, productName, domainTypeId});
		return true;//Need to handle failure cases ://TODO
	}
	

	@Override
	public Product getProductById(String prodId) {
		String sql = "SELECT * FROM product WHERE product_id = ?";

		return (Product) getJdbcTemplate().queryForObject(sql, new Object[] { prodId }, new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Product product = new Product();
				product.setCustomerId(rs.getString("customer_id"));
				product.setProductName(rs.getString("product_name"));
				product.setDomain(rs.getString("domaintype_id"));
				product.setStartDate(rs.getString("start_date"));
				product.setDurationMonths(rs.getInt("duration_months"));
				 
				return product;
			}
		});
		 
	}
}



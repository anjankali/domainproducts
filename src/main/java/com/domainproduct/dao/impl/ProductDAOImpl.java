package com.domainproduct.dao.impl;

import java.util.List;

import com.domaintest.model.Product;

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
	public void insertProducts(final List<Product> employees) {
		String sql = "INSERT INTO product " + "(empId, empName) VALUES (?, ?)";
		getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				/*
				 * Employee employee = employees.get(i); ps.setString(1, employee.getEmpId());
				 * ps.setString(2, employee.getEmpName());
				 */
			}
			
			public int getBatchSize() {
				return employees.size();
			}
		});

	}
	@Override
	public List<Product> getAllProducts(){
		String sql = "SELECT * FROM employee";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		List<Product> result = new ArrayList<Product>();
		for(Map<String, Object> row:rows){
			Product emp = new Product();
			/*
			 * emp.setEmpId((String)row.get("empId"));
			 * emp.setEmpName((String)row.get("empName"));
			 */
			result.add(emp);
		}
		
		return result;
	}

	@Override
	public Product getProductById(String prodId) {
		String sql = "SELECT * FROM product WHERE product_id = ?";

		return (Product) getJdbcTemplate().queryForObject(sql, new Object[] { prodId }, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Product product = new Product();
				product.setCustomerId(rs.getString("empId"));
				product.setProductName(rs.getString("empId"));
				product.setDomain(rs.getString("empId"));
				product.setStartDate(rs.getString("empId"));
				product.setDurationMonths(rs.getInt("empId"));
				return product;
			}
		});
		 
	}
}



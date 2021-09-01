package com.domainproduct.dao.impl;

import java.util.List;

import com.domainproduct.model.Product;

public interface ProductDAO {
	void insertProduct(Product product);

	void insertProducts(List<Product> products);

	List<Product> getAllProducts();

	boolean deleteProduct(String customerId, String productName, String domain);
}

package com.domainproduct.service;

import java.util.List;

import com.domainproduct.model.Product;


public interface ProductService {

	void insertProduct(Product product);

	void insertProducts(List<Product> products);

	List<Product> getAllProducts();

	void deleteProduct(String customerId, String productName, String domain);
}

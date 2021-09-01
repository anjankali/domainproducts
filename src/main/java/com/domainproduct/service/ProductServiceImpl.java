package com.domainproduct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domainproduct.dao.impl.ProductDAO;
import com.domainproduct.model.Product;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDao;

	@Override
	public void insertProduct(Product product) {
		productDao.insertProduct(product);
	}

	@Override
	public void insertProducts(List<Product> products) {
		productDao.insertProducts(products);
	}
	
	public List<Product> getAllProducts() {
		List<Product> Products = productDao.getAllProducts();
		for (Product product : Products) {
			System.out.println(product.toString());
		}
		
		return Products;
	}

	@Override
	public Product getProductById(String productId) {
		Product product = productDao.getProductById(productId);
		System.out.println(product);
		
		return product;
	}
	
	@Override
	public void deleteProduct(String customerId, String productName, String domainTypeId) {
		boolean status = productDao.deleteProduct(customerId, productName, domainTypeId);
		if(status) {
			System.out.println("Prduct deleted sucessfully");	
		}else {
			System.out.println("Prduct deleted Failed");
		}
		
	}
	
}

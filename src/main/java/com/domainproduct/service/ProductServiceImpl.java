package com.domainproduct.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domainproduct.dao.impl.ProductDAO;
import com.domainproduct.model.Product;

@Service
@RestController
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDao;

	@Override
	@RequestMapping("/createproduct")
	public void insertProduct(Product product) {
		productDao.insertProduct(product);
	}

	@Override
	@PostMapping("/createproduct")
	public void insertProducts(List<Product> products) {
		productDao.insertProducts(products);
	}
	
	@GetMapping("/list")
	public List<Product> getAllProducts() {
		List<Product> Products = productDao.getAllProducts();
		for (Product product : Products) {
			System.out.println(product.toString());
		}
		
		return Products;
	}

	@Override
	@DeleteMapping("/product/{customerId}/{productName}/{domainTypeId}")
	public void deleteProduct(String customerId, String productName, String domainTypeId) {
		boolean status = productDao.deleteProduct(customerId, productName, domainTypeId);
		if(status) {
			System.out.println("Prduct deleted sucessfully");	
		}else {
			System.out.println("Prduct deleted Failed");
		}
		
	}
	
}

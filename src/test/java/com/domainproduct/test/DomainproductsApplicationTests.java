package com.domainproduct.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.domainproduct.model.Product;
import com.domainproduct.service.ProductService;
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ProductService.class})
@WebMvcTest
class DomainProductTests {

	@Autowired
	ProductService productService;
	
	static {
		ApplicationContext context = SpringApplication.run(com.domainproducts.DomainproductsApplication.class, new String[] {});
		ProductService productService = context.getBean(ProductService.class);
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testcreateProduct() throws Exception {
		//customer_id,  product_name,  domaintype_id,  start_date, duration_months
		Product product= new Product();
		product.setCustomerId("anjan");
		product.setProductName("coding");
		product.setDomain("10");
		product.setStartDate("31-08-2021 12:12:12");
		product.setDurationMonths(12);
		
		productService.insertProduct(product);
    }
    
    @Test
    public void testcreateProducts() throws Exception {
		//customer_id,  product_name,  domaintype_id,  start_date, duration_months
		Product product= new Product();
		product.setCustomerId("anjan");
		product.setProductName("coding");
		product.setDomain("10");
		product.setStartDate("31-08-2021 12:12:12");
		product.setDurationMonths(12);
		
		Product product1= new Product();
		product1.setCustomerId("anjan");
		product1.setProductName("coding");
		product1.setDomain("10");
		product1.setStartDate("31-08-2021 12:12:12");
		product1.setDurationMonths(12);
		
		List<Product> products = new ArrayList<>();
		products.add(product);
		products.add(product1);
		
		productService.insertProducts(products);
    }
    
    @Test
    public void getAllProducts() throws Exception {
    	List<Product> listData = productService.getAllProducts();
    }
    
    @Test
    public void deleteProduct() throws Exception {
    	String customerId="anjan";
    	String productName="coding";
    	String domain="10";
    	productService.deleteProduct(customerId, productName, domain);
    }
    
    @Test
    public void getProductById() throws Exception {
    	
    }
    
    //Etc Test cases need to write
	
}
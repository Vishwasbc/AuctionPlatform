package com.product.service;

import java.util.List;

import com.product.entity.Product;

public interface ProductService {

	List<Product> getAllProducts();

	Product getProductById(int id);

	Product saveProduct(Product product);

}

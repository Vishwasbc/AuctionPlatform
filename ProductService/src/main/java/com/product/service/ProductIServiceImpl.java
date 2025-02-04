package com.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.dto.UserDTO;
import com.product.entity.Product;
import com.product.feign.UserClient;
import com.product.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service  
@AllArgsConstructor
@NoArgsConstructor
public class ProductIServiceImpl implements ProductService {
	private ProductRepository productRepository;
	private UserClient userClient;
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	private UserDTO getSellerDetails(String sellerName) {
		return userClient.getUser(sellerName);
	}

}

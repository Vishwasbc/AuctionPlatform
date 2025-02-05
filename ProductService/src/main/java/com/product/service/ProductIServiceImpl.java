package com.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.dto.ProductDTO;
import com.product.dto.UserDTO;
import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;
import com.product.exception.UserNotFoundException;
import com.product.feign.UserClient;
import com.product.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductIServiceImpl implements ProductService {
	private ProductRepository productRepository;
	private UserClient userClient;

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product with id:" + id + " not Found"));
	}

	@Override
	public Product saveProduct(Product product) {
		UserDTO user = userClient.getByUserName(product.getSellerName());
		if (user == null) {
			throw new UserNotFoundException("Invalid Seller");
		}
		return productRepository.save(product);
	}

	@Override
	public String deleteProduct(int id) {
		productRepository.deleteById(id);
		return "Product Deleted";
	}

	@Override
	public Product updateProduct(int id, Product product) {
		UserDTO user = userClient.getByUserName(product.getSellerName());
		if (user == null) {
			throw new UserNotFoundException("Invalid Seller");
		}
		Product update = new Product();
		update.setProductId(id);
		update.setProductName(product.getProductName());
		update.setProductDescription(product.getProductDescription());
		update.setPrice(product.getPrice());
		update.setSellerName(product.getSellerName());
		return productRepository.save(update);
	}

	@Override
	public ProductDTO getByProductId(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product with id:" + id + " not Found"));
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductDescription(product.getProductDescription());
		productDTO.setPrice(product.getPrice());
		productDTO.setSellerName(product.getSellerName());
		return productDTO;
	}

}

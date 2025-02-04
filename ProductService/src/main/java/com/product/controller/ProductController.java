package com.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

	private ProductService productService;
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		return ResponseEntity.ok(productService.getAllProducts());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id){
		return ResponseEntity.ok(productService.getProductById(id));
	}
	@PostMapping
	private ResponseEntity<Product> saveProduct(@RequestBody Product product){
		return ResponseEntity.ok(productService.saveProduct(product));
	}
}

package com.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductDTO;
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
	@GetMapping("/get")
	public ResponseEntity<Product> getProduct(@RequestParam int id){
		return ResponseEntity.ok(productService.getProductById(id));
	}
	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		return new ResponseEntity<>(productService.saveProduct(product),HttpStatus.CREATED);
	}
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestParam int id,@RequestBody Product product){
		return ResponseEntity.ok(productService.updateProduct(id,product));
	}
	@DeleteMapping
	public ResponseEntity<String> deleteProduct(@RequestParam int id){
		return ResponseEntity.ok(productService.deleteProduct(id));
	}
	@GetMapping("{id}")
	public ProductDTO getByProductId(@PathVariable int id) {
		return productService.getByProductId(id);
	}
}

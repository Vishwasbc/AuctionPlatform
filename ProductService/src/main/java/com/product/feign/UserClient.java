package com.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.product.dto.UserDTO;

@FeignClient(name = "user-service",url = "http://localhost:8201/api/user")
public interface UserClient {
	@GetMapping("/username")
	UserDTO getUser(@PathVariable String username);
}

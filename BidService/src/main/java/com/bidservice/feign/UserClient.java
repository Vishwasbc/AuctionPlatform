package com.bidservice.feign;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bidservice.dto.UserDto;

 
@FeignClient("USERSERVICE")
@RequestMapping("/api/user/")
public interface UserClient {
	@GetMapping("/{userName}")
	public UserDto getByUserName(@PathVariable String userName);
}
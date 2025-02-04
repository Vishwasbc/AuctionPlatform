package com.bidservice.feign;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bidservice.dto.UserDto;
 
@FeignClient(name = "user-service", url = "http://localhost:8201/api/users")
public interface UserClient {
    @GetMapping("/{username}")
    UserDto getUserByUsername(@PathVariable String username);
}
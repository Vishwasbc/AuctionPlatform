package com.userservice.controller;

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

import com.userservice.DTO.UserDTO;
import com.userservice.entity.User;
import com.userservice.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
	private UserService userService;
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password) {
		return ResponseEntity.ok(userService.loginUser(userName, password));
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam String userName) {
		return ResponseEntity.ok(userService.deleteUser(userName));
	}

	@PutMapping("/update/{userName}")
	public ResponseEntity<User> update(@PathVariable String userName, @RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(userName, user));
	}

	@GetMapping("/{userName}")
	public UserDTO getByUserName(@PathVariable String userName) {
		return userService.getByUserName(userName);
	}
}

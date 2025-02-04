package com.userservice.service;

import com.userservice.DTO.UserDTO;
import com.userservice.entity.User;

public interface UserService {
	String loginUser(String userName, String password);

	User registerUser(User user);

	String deleteUser(String userName);

	User updateUser(String userName, User user);

	UserDTO getByUserName(String userName);
}

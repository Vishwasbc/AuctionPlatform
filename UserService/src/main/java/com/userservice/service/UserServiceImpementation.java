package com.userservice.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.userservice.DTO.UserDTO;
import com.userservice.entity.User;
import com.userservice.exception.IncorrectPasswordException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpementation implements UserService {

	private UserRepository userRepository;

	@Override
	public String loginUser(String userName, String password) {
		User user = userRepository.findById(userName).orElseThrow(() -> new UserNotFoundException( userName+" not Found"));
		if (!Objects.equals(user.getPassword(), password)) {
			throw new IncorrectPasswordException("Wrong Password");
		}
		return "Logged In";
	}

	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public String deleteUser(String userName) {
		User user = userRepository.findById(userName).orElseThrow(() -> new UserNotFoundException("User not Found"));
		userRepository.delete(user);
		return "User Deleted";
	}

	@Override
	public User updateUser(String userName, User user) {
		User existingUser = userRepository.findById(userName)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setContactNo(user.getContactNo());
		existingUser.setBirthDate(user.getBirthDate());
		existingUser.setGender(user.getGender());

		return userRepository.save(existingUser);
	}

	@Override
	public UserDTO getByUserName(String userName) {
		User user = userRepository.findById(userName).orElseThrow(() -> new UserNotFoundException(userName+" not Found"));
		UserDTO userDTO = new UserDTO();
		userDTO.setUserName(user.getUserName());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		userDTO.setRole(user.getRole());
		return userDTO;
	}

}

package com.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.userservice.DTO.UserDTO;
import com.userservice.entity.Role;
import com.userservice.entity.User;
import com.userservice.exception.IncorrectPasswordException;
import com.userservice.exception.UserNotFoundException;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserServiceImpementation;

public class UserServiceApplicationTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpementation userService;

	private User user;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		user = new User("john_doe", "John", "Doe", "john.doe@example.com", "Password123!", "1234567890", new Date(),
				"Male",Role.ADMIN);
	}

	@Test
	public void testLoginUser_Success() {
		when(userRepository.findById("john_doe")).thenReturn(Optional.of(user));
		String result = userService.loginUser("john_doe", "Password123!");
		assertEquals("Logged In", result);
	}

	@Test
	public void testLoginUser_IncorrectPassword() {
		when(userRepository.findById("john_doe")).thenReturn(Optional.of(user));
		assertThrows(IncorrectPasswordException.class, () -> {
			userService.loginUser("john_doe", "WrongPassword");
		});
	}

	@Test
	public void testLoginUser_UserNotFound() {
		when(userRepository.findById("john_doe")).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			userService.loginUser("john_doe", "Password123!");
		});
	}

	@Test
	public void testRegisterUser() {
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.registerUser(user);
		assertEquals(user, result);
	}

	@Test
	public void testDeleteUser_Success() {
		when(userRepository.findById("john_doe")).thenReturn(Optional.of(user));
		String result = userService.deleteUser("john_doe");
		assertEquals("User Deleted", result);
		verify(userRepository, times(1)).delete(user);
	}

	@Test
	public void testDeleteUser_UserNotFound() {
		when(userRepository.findById("john_doe")).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			userService.deleteUser("john_doe");
		});
	}

	@Test
	public void testUpdateUser() {
		when(userRepository.findById("john_doe")).thenReturn(Optional.of(user));
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.updateUser("john_doe", user);
		assertEquals(user, result);
	}

	@Test
	public void testGetByUserName_Success() {
		when(userRepository.findById("john_doe")).thenReturn(Optional.of(user));
		UserDTO result = userService.getByUserName("john_doe");
		assertEquals(user, result);
	}

	@Test
	public void testGetByUserName_UserNotFound() {
		when(userRepository.findById("john_doe")).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			userService.getByUserName("john_doe");
		});
	}
}

package com.userservice.DTO;

import com.userservice.entity.Role;

import lombok.Data;

@Data
public class UserDTO {
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
}

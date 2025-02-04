package com.userservice.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private String userName;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@Email
	@NotNull
	private String email;
	@NotNull
	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$", message = "Password must contain at least one digit, one letter, and be at least 8 characters long")
	private String password;
	@NotNull
	@Pattern(regexp = "^\\d{10}$", message = "Contact number must be exactly 10 digits")
	private String contactNo;
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthDate;
	@NotNull
	private String gender;
	@Enumerated(EnumType.STRING)
	private Role role = Role.DEFAULT;
}

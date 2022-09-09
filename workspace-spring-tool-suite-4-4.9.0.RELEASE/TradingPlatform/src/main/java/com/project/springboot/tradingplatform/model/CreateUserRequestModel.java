package com.project.springboot.tradingplatform.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {
private Long id; 

@NotNull(message="name cannot be null")
@Size(min=2)
private String name;
@NotNull(message="name cannot be null")
@Email
private String email;
private int balance;
public int getBalance() {
	return balance;
}
public void setBalance(int balance) {
	this.balance = balance;
}
private String createdAt;
private String updatedAt;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
}
public String getUpdatedAt() {
	return updatedAt;
}
public void setUpdatedAt(String updatedAt) {
	this.updatedAt = updatedAt;
}
}

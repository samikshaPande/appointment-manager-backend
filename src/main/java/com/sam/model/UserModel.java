package com.sam.model;

import jakarta.persistence.UniqueConstraint;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users", 
uniqueConstraints = { 
  @UniqueConstraint(columnNames = "userName")
})
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userID;

	private String userName;

	private String password;

	private String role;

	public UserModel() {

	}

	public UserModel(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public Long getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

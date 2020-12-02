package com.ofr.usermodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "UserTable")
public class User {

	@Id
	@Column(length = 15)
	private Integer userId;
	@Column(length = 15)
	@NotNull
	@Size(min = 5, max = 10, message = "User Name should have Minimum 5 and Maximum 10 Characters")
	private String userName;
	@Column(length = 15)
	@NotNull
	@Pattern(regexp =  "^[(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8, 15}]*$", message = "Password should be AlphaNumeric and contain atleast One Special Character")
	private String password;
	@Column(length = 15)
	private String userType;
	public User() {
		
	}
	public User(Integer userId, String userName, String password, String userType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", userType=" + userType
				+ "]";
	}
	
	
}

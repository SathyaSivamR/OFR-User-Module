 package com.ofr.userservice;

 /*
  * Flat Service Interface
  *
  * @Author : Sathya Sivam R
  * 
 */

import java.util.List;
import java.util.Optional;

import com.ofr.userexception.DuplicateUserRecordException;
import com.ofr.userexception.RegisterationException;
import com.ofr.userexception.UserNotFoundException;
import com.ofr.usermodel.User;

public interface UserService {

	public User addUser(User user) throws DuplicateUserRecordException, RegisterationException;
	
	public User updateUser(User user) throws UserNotFoundException;

	public User removeUser(User user) throws UserNotFoundException;

	public Optional<User> viewUser(Integer userId) throws UserNotFoundException;
	
	public List<User> viewAllUser();
		
	public String validateUser(Integer userId, String userName,String password) throws UserNotFoundException; 
	
	public String updatePassword(User user,String newpass) throws UserNotFoundException;

}

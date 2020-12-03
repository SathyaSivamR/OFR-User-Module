package com.ofr.userservice;

/*
 * Flat Service Implementation class
 * 
 * @Author : Sathya Sivam R
*/

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofr.userdao.UserDao;
import com.ofr.userexception.DuplicateUserRecordException;
import com.ofr.userexception.RegisterationException;
import com.ofr.userexception.UserNotFoundException;
import com.ofr.usermodel.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	String userNotFound = "User Not Existed";
	
	/*
	 * This method is used for add the user
	 * return type User
	 * @param user
	 * exception RegisterationException,DuplicateUserRecordException
	 */
	@Override
	public User addUser(User user) throws DuplicateUserRecordException, RegisterationException{
		
		if(user.getUserName().isEmpty() || user.getPassword().isEmpty() || user.getUserType().isEmpty())
		{
			throw new RegisterationException("Enter the valid Details");
		}
		
		Optional<User> user1 = userDao.findById(user.getUserId());
		if(user1.isPresent())
		{
			throw new DuplicateUserRecordException("User Already Existed");
		}
		
		return getUserDao().save(user);
	}

	/*
	 * This method is used for update the user
	 * return type user
	 * @param user
	 * exception UserNotFoundException
	 */
	@Override
	public User updateUser(User user) throws UserNotFoundException{
		Optional<User> user1 = userDao.findById(user.getUserId());
		if(user1.isPresent())
		{
			return getUserDao().save(user);
		}
		else
		{
			throw new UserNotFoundException(userNotFound);
		}
	}

	/*
	 * This method is used for delete the user
	 * return type user
	 * @param user
	 * exception UserNotFoundException
	 */
	@Override
	public User removeUser(User user) throws UserNotFoundException{
		Optional<User> user1 = userDao.findById(user.getUserId());
		if(user1.isPresent())
		{
			getUserDao().delete(user);
		}
		else
		{
			throw new UserNotFoundException(userNotFound);
		}
		return user;
	}

	/*
	 * This method is used for viewing the user by id
	 * return type user
	 * @param user
	 * exception UserNotFoundException
	 */
	@Override
	public Optional<User> viewUser(Integer userId) throws UserNotFoundException {
		Optional<User> user = getUserDao().findById(userId);
		if(user.isPresent())
		{
			return getUserDao().findById(userId);
		}
		else
		{
			throw new UserNotFoundException(userNotFound);
		}
	}

	/*
	 * This method is used for view all the user
	 * return type list
	 * @param user
	 */
	@Override
	public List<User> viewAllUser() {

		return getUserDao().findAll();
	}

	/*
	 * This method is used for validate the user's username and password 
	 * @param user, username, password return type String
	 * exception UserNotFoundException
	 */
	@Override
	public String validateUser(Integer userId, String userName, String password) throws UserNotFoundException {

		Optional<User> user = getUserDao().findById(userId);
		if(user.isPresent())
		{
			if((userName.equals(user.get().getUserName())) && (password.equals(user.get().getPassword())))
			{
				return "Validated";
			}
			else
			{
				throw new UserNotFoundException("User Not Found");
			}
		}
		return "Given User ID Not Found";
	}

	/*
	 * This method is used for update the password of particular user
	 * First it checks the old username and password is valid or not then it changes the new password
	 * return type String 
	 * @param user, new password
	 * exception UserNotFoundException
	 */
	@Override
	public String updatePassword(User user, String newpass) throws UserNotFoundException {

		String check = validateUser(user.getUserId(), user.getUserName(), user.getPassword());
		if(check.equals("Validated"))
		{
			user.setPassword(newpass);
			getUserDao().save(user);
			return "Password Changed Successfully!!!";
		}
		else
		{
			throw new UserNotFoundException("User Not Found");
		}

	}

}

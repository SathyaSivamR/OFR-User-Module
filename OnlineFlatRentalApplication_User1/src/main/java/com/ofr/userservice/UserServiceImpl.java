package com.ofr.userservice;

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

	@Override
	public List<User> viewAllUser() {

		return getUserDao().findAll();
	}

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

package com.ofr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.Severity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import com.ofr.userdao.UserDao;
import com.ofr.userexception.DuplicateUserRecordException;
import com.ofr.userexception.RegisterationException;
import com.ofr.userexception.UserNotFoundException;
import com.ofr.usermodel.User;
import com.ofr.userservice.UserServiceImpl;


@TestInstance(Lifecycle.PER_CLASS)
class UserServiceImplTest {

	@Mock
	UserServiceImpl service;
	
	@Mock
	UserDao dao;
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testAddUser() throws DuplicateUserRecordException, RegisterationException {
		User user = new User(1, "Sathya", "123456", "Tenant");
		service.addUser(user);
		
		verify(service, times(1)).addUser(user);
	}
	
	@Test
	void testUpdateUser() throws DuplicateUserRecordException, RegisterationException, UserNotFoundException {
		User user = new User(2, "Sathya", "123456", "Tenant");
		service.addUser(user);
		
		user.setUserName("Sandy");
		user.setUserType("Landord");
		
		service.updateUser(user);
		
		verify(service, times(1)).addUser(user);
		verify(service, times(1)).updateUser(user);
	}

	@Test
	void testRemoveUser() throws DuplicateUserRecordException, RegisterationException, UserNotFoundException {
		User user = new User(3, "Sathya", "123456", "Tenant");
		service.addUser(user);
		
		User user1 = service.removeUser(user);
		
		verify(service, times(1)).addUser(user);
		verify(service, times(1)).removeUser(user);
		
		assertNull(user1);
	}

	@Test
	void testViewUser() throws UserNotFoundException {

		Optional<User> trainee1 = Optional.ofNullable(new User(4, "Sathya", "123456789", "Tenant"));
		
		when(service.viewUser(4)).thenReturn(trainee1);
		
		Optional<User> findUserById = service.viewUser(4);

		verify(service, times(1)).viewUser(4);
		
		assertEquals(trainee1, findUserById);
	}

	@Test
	void testViewAllUser() throws DuplicateUserRecordException, RegisterationException {
		User user1 = new User(1, "Sathya", "123456", "Tenant");
		User user2 = new User(2, "Sandy", "123456", "Landlord");
		User user3 = new User(3, "Kezii", "123456", "Tenant");
		service.addUser(user1);
		service.addUser(user2);
		service.addUser(user3);
		
		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		
		when(service.viewAllUser()).thenReturn(userList);

		List<User> getList = service.viewAllUser();
		verify(service, times(1)).viewAllUser();
		
		assertEquals(3, getList.size());
	}

	@Test
	void testValidateUser() throws DuplicateUserRecordException, RegisterationException, UserNotFoundException {

		User user1 = new User(10, "Sathya", "123456", "Tenant");
		service.addUser(user1);
		
		when(service.validateUser(10, "Sathya", "123456")).thenReturn("Validated");
		Optional<User> valid = dao.findById(10);
		
		String check = service.validateUser(10, "Sathya", "123456");
		verify(service, times(1)).validateUser(10, "Sathya", "123456");
		
		assertNotNull(valid);
		assertEquals("Validated", check);
	}
	
	@Test
	void testUpdatePassword() throws DuplicateUserRecordException, RegisterationException, UserNotFoundException
	{
		User user1 = new User(10, "Sathya", "123456", "Tenant");
		service.addUser(user1);
		
		user1.setPassword("987654");
		dao.save(user1);
		assertEquals("987654", user1.getPassword());
		
	}
	

}

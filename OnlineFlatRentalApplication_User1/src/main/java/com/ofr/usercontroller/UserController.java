package com.ofr.usercontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofr.userexception.DuplicateUserRecordException;
import com.ofr.userexception.RegisterationException;
import com.ofr.userexception.UserNotFoundException;
import com.ofr.usermodel.User;
import com.ofr.userservice.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	 public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}


	@GetMapping("/all")//http://localhost:7878/trainees/all
	    public ResponseEntity<List<User>> fetchAllUsers() 
	 	{
	        List<User> list = getService().viewAllUser();
	        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);//200,json
	    }
	 
	 
	 @PostMapping("/add")
		 public ResponseEntity<Boolean> create(@RequestBody User user) throws DuplicateUserRecordException, RegisterationException
		 {
			 getService().addUser(user);
			 @SuppressWarnings({ "rawtypes", "unchecked" })
			ResponseEntity<Boolean> responseEntity = new ResponseEntity(true, HttpStatus.OK);
			 return responseEntity;
		 }
	 
	 @PutMapping("/update")
		 public ResponseEntity<Boolean> update(@RequestBody User user) throws UserNotFoundException
		 {
			 getService().updateUser(user);
			 @SuppressWarnings({ "rawtypes", "unchecked" })
			ResponseEntity<Boolean> responseEntity = new ResponseEntity(true, HttpStatus.OK);
			 return responseEntity;
		 }
	 
	 @DeleteMapping("/remove")
		 public ResponseEntity<Boolean> delete(@RequestBody User user) throws UserNotFoundException
		 {
			 getService().removeUser(user);
			 @SuppressWarnings({ "rawtypes", "unchecked" })
			ResponseEntity<Boolean> responseEntity = new ResponseEntity(true, HttpStatus.OK);
			 return responseEntity;
		 }
	 
	 @GetMapping("/find/{userId}")
	 	public ResponseEntity<Optional<User>> findTraineeById(@PathVariable("userId") Integer userId) throws UserNotFoundException
	 	{
	 		Optional<User> user = getService().viewUser(userId);
	 		@SuppressWarnings({ "rawtypes", "unchecked" })
			ResponseEntity<Optional<User>> responseEntity = new ResponseEntity(user, HttpStatus.OK);
	 		return responseEntity;
	 	}
	 
	 @GetMapping("/validate/{id}/{userName}/{password}")
		 public String validateUser(@PathVariable Integer id, @PathVariable String userName, @PathVariable String password) throws UserNotFoundException
		 {
			 return getService().validateUser(id, userName, password);
		 }
	 
	 @PutMapping("/updatePass/{newPass}")
		 public String updatePassword(@RequestBody User user, @PathVariable String newPass) throws UserNotFoundException
		 {
			 return getService().updatePassword(user, newPass);
		 }
}

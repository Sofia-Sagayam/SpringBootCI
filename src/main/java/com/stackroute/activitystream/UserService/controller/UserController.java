package com.stackroute.activitystream.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.activitystream.UserService.dao.UserDao;
import com.stackroute.activitystream.UserService.model.User;

@RestController
public class UserController {
	@Autowired
	private UserDao userDao;
	@RequestMapping(value="/alluser",method = RequestMethod.GET)
	public HttpEntity<List<User>> getCustomers() {
		List<User> users=userDao.getUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value="/signup",method= RequestMethod.POST)
	  public ResponseEntity<String> registerUser(@RequestBody User user){
		if(userDao.registerUser(user))
		{
		return new ResponseEntity<String>("registered successfully!!..",HttpStatus.CREATED);
	  }
		return new ResponseEntity<String>("mailid registered already,pls use new mailid",HttpStatus.NOT_ACCEPTABLE);
		
	  }
	@RequestMapping(value="/signin",method= RequestMethod.POST)
	  public ResponseEntity<String> loginUser(@RequestBody User user){
		  //send email id and password to dao.  it should return true or false
		
		if(userDao.validateUser(user.getEmailid(), user.getUpass()))
		{
		return new ResponseEntity<String>("welcome!!! ",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("oops wrong mailid or password...",HttpStatus.NOT_FOUND);
		
	  }
	  @RequestMapping(value="/updateUser",method= RequestMethod.PUT)
	  public ResponseEntity<String> updateUser(@RequestBody User user){
		  if(userDao.updateUser(user)){
		return new ResponseEntity<String>("updated successfully...",HttpStatus.OK);
		}
		return new ResponseEntity<String>("oops email id is not registerd...",HttpStatus.NOT_FOUND);
				
	  }
	  
	  @DeleteMapping(value="/deleteUser/{id}")
	  public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
		 
		  if(userDao.deleteUser(id)){
		return new ResponseEntity<String>("deleted successfully,,,",HttpStatus.OK);
		}
		return new ResponseEntity<String>("oops email id is not registerd...",HttpStatus.NOT_FOUND);
				
	  }
	  
}

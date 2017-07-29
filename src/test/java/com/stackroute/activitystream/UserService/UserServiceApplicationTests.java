package com.stackroute.activitystream.UserService;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.UserService.dao.UserDao;
import com.stackroute.activitystream.UserService.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceApplicationTests {
	@Autowired
	private UserDao userDao;
	private static User user;
	@BeforeClass
	public static void initiate(){
		user=new User();
	}
	@Test
	public void saveUser() {
	user.setEmailid("jayashree@niit.com");
	user.setUname("jaya");
	user.setUpass("jaya#321");
	user.setUaddress("guduvancherry chennai");
	userDao.registerUser(user);
	User found=userDao.getUserbyId(user.getEmailid());
	assertNotNull(found);
	
	}

	@Test
	public void reteriveUser(){
		User reterivedData=	userDao.getUserbyId("jk@gmail.com");
		assertNotNull(reterivedData);
	}
	@Test
	public void updateUser(){
		User reterivedData=	userDao.getUserbyId("jk@gmail.com");
		reterivedData.setUname("jai kutty");
		assertTrue(userDao.updateUser(reterivedData));
	}
	@Test
	public void deleteUser(){
		assertTrue(userDao.deleteUser("dummy@nit.com"));
	}
	

}

package com.stackroute.activitystream.UserService.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.activitystream.UserService.model.User;
@Repository(value="userDao")
@Component
@Transactional
@EnableTransactionManagement
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private List<User> listOfUser;

public boolean registerUser(User user) {
	try{
	sessionFactory.getCurrentSession().save(user);
	
	return true;
	}
	catch(Exception e){
		e.printStackTrace();
	return false;}
}
public boolean updateUser(User user) {
	
try{
	sessionFactory.getCurrentSession().saveOrUpdate(user);
	return true;
}
catch(Exception e){
return false;}
}
public List<User> getUsers() {
	try{
		listOfUser=sessionFactory.getCurrentSession().createQuery("from User").list();
	}
	catch(Exception e){
		
	}
	return listOfUser;
}

public boolean validateUser(String userid,String userpass){
	try{
	listOfUser=sessionFactory.openSession().createQuery("from User u where u.emailid=? and u.upass=?").setParameter(0, userid).setParameter(1, userpass).list();
	}
	catch(Exception e){}
	return listOfUser.size()>0?true:false;
}

public boolean deleteUser(String mailID) {
	try{
		sessionFactory.getCurrentSession().delete(getUserbyId(mailID));
		return true;
	}
	catch(Exception e){
		e.printStackTrace();
	return false;}
	}

public User getUserbyId(String mailid) {
	try{
	return sessionFactory.getCurrentSession().get(User.class, mailid);
	}
	catch(Exception e){}
	return null;
}

}

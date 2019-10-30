package webapp.service;

import java.math.BigInteger;
import java.util.ArrayList;

import webapp.model.User;
import webapp.model.UserJPA;
import webapp.model.UserMongo;

public interface UserService {
	
	ArrayList<UserMongo> findAll();
	
	UserMongo findById(BigInteger id);
	
	UserMongo findByUsername(String username);
	
	UserMongo findByEmail(String email);
	
	UserMongo create(UserMongo user);
	
	UserMongo update(UserMongo user);
	
	UserMongo updatePassword(UserMongo user);
	
	void delete(UserMongo user);
	
	void deleteById(BigInteger id);
}

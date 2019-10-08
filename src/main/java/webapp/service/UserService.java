package webapp.service;

import java.util.ArrayList;

import webapp.model.User;

public interface UserService {
	
	ArrayList<User> findAll();
	
	User findById(String id);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User create(User user);
	
	User update(User user);
	
	void delete(User user);
	
	void deleteById(String id);
}

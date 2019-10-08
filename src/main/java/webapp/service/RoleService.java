package webapp.service;

import java.util.ArrayList;

import webapp.model.Role;

public interface RoleService {
	
	ArrayList<Role> findAll();
	
	Role findById(String id);

	Role findByName(String name);
	
	Role create(Role role);
	
	Role update(Role role);
	
	void delete(Role role);
	
	void deleteById(String id);
}

package webapp.service;

import java.math.BigInteger;
import java.util.ArrayList;

import webapp.model.Role;
import webapp.model.RoleJPA;
import webapp.model.RoleMongo;

public interface RoleService {
	
	ArrayList<RoleMongo> findAll();
	
	RoleMongo findById(BigInteger id);

	RoleMongo findByName(String name);
	
	RoleMongo create(RoleMongo role);
	
	RoleMongo update(RoleMongo role);
	
	void delete(RoleMongo role);
	
	void deleteById(BigInteger id);
}

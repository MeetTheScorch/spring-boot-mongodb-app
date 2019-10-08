package webapp.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import webapp.model.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

	ArrayList<Role> findAll();
	
	Optional<Role> findById(String id);
	
	Role findByName(String name);
}

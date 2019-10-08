package webapp.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import webapp.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	ArrayList<User> findAll();
	
	Optional<User> findById(String id);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
}
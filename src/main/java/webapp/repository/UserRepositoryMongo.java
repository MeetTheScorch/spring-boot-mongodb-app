package webapp.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import webapp.model.UserMongo;

public interface UserRepositoryMongo extends MongoRepository<UserMongo, BigInteger>{
	
	ArrayList<UserMongo> findAll();
	
	Optional<UserMongo> findById(BigInteger id);
	
	UserMongo findByUsername(String username);
	
	UserMongo findByEmail(String email);
}
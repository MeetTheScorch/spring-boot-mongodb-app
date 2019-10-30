package webapp.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import webapp.model.RoleMongo;

public interface RoleRepositoryMongo extends MongoRepository<RoleMongo, BigInteger>{

	ArrayList<RoleMongo> findAll();
	
	Optional<RoleMongo> findById(BigInteger id);
	
	RoleMongo findByName(String name);
}

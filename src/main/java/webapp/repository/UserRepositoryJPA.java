package webapp.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webapp.model.UserJPA;

public interface UserRepositoryJPA extends JpaRepository<UserJPA, BigInteger>{
	
	ArrayList<UserJPA> findAll();
	
	Optional<UserJPA> findById(BigInteger id);
	
	UserJPA findByUsername(String username);
	
	UserJPA findByEmail(String email);
}
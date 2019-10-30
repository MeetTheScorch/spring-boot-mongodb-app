package webapp.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webapp.model.RoleJPA;

public interface RoleRepositoryJPA extends JpaRepository<RoleJPA, BigInteger>{

	ArrayList<RoleJPA> findAll();
	
	Optional<RoleJPA> findById(BigInteger id);
	
	RoleJPA findByName(String name);
}

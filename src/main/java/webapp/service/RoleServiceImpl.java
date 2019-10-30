package webapp.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.model.Role;
import webapp.model.RoleJPA;
import webapp.model.RoleMongo;
import webapp.repository.RoleRepositoryJPA;
import webapp.repository.RoleRepositoryMongo;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepositoryMongo roleRepository;

	@Override
	public ArrayList<RoleMongo> findAll() {
		return roleRepository.findAll();
	}
	
	@Override
	public RoleMongo findById(BigInteger id) {
		Optional<RoleMongo> role = roleRepository.findById(id);
		if(role.isPresent())
			return role.get();
		else
			return null;
	}
	
	@Override
	public RoleMongo findByName(String name) {
		return roleRepository.findByName(name);
	}
	
	@Override
	public RoleMongo create(RoleMongo role) {
		return roleRepository.save(role);
	}

	@Override
	public RoleMongo update(RoleMongo role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(RoleMongo role) {
		roleRepository.delete(role);
	}
	
	@Override
	public void deleteById(BigInteger id) {
		roleRepository.deleteById(id);
	}
}

package webapp.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.model.Role;
import webapp.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public ArrayList<Role> findAll() {
		return roleRepository.findAll();
	}
	
	@Override
	public Role findById(String id) {
		Optional<Role> role = roleRepository.findById(id);
		if(role.isPresent())
			return role.get();
		else
			return null;
	}
	
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	
	@Override
	public Role create(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(Role role) {
		roleRepository.delete(role);
	}
	
	@Override
	public void deleteById(String id) {
		roleRepository.deleteById(id);
	}
}

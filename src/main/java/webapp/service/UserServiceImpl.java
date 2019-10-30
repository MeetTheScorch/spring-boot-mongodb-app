package webapp.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import webapp.model.Role;
import webapp.model.RoleJPA;
import webapp.model.RoleMongo;
import webapp.model.User;
import webapp.model.UserJPA;
import webapp.model.UserMongo;
import webapp.repository.RoleRepositoryJPA;
import webapp.repository.RoleRepositoryMongo;
import webapp.repository.UserRepositoryJPA;
import webapp.repository.UserRepositoryMongo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepositoryMongo userRepository;
	
	@Autowired
	private RoleRepositoryMongo roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ArrayList<UserMongo> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public UserMongo findById(BigInteger id) {
		Optional<UserMongo> user = userRepository.findById(id);
		if(user.isPresent())
			return user.get();
		else
			return null;
	}
	
	@Override
	public UserMongo findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public UserMongo findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public UserMongo create(UserMongo user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if(user.getRoles().isEmpty()) {
			ArrayList<RoleMongo> roles = new ArrayList<>();
			roles.add(roleRepository.findByName("USER"));
			user.setRoles(roles);
		}
		return userRepository.save(user);
	}

	@Override
	public UserMongo update(UserMongo user) {
		UserMongo oldUser = findById(user.getId());
		user.setPassword(oldUser.getPassword());
		user.setRoles(oldUser.getRoles());
		return userRepository.save(user);
	}
	
	public UserMongo updatePassword(UserMongo user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void delete(UserMongo user) {
		userRepository.delete(user);
	}
	
	@Override
	public void deleteById(BigInteger id) {
		userRepository.deleteById(id);
	}
	
	
}

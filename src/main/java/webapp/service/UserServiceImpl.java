package webapp.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import webapp.model.Role;
import webapp.model.User;
import webapp.repository.RoleRepository;
import webapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ArrayList<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent())
			return user.get();
		else
			return null;
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		ArrayList<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findByName("USER"));
		user.setRoles(roles);
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		User oldUser = findById(user.getId());
		user.setPassword(oldUser.getPassword());
		user.setRoles(oldUser.getRoles());
		return userRepository.save(user);
	}
	
	public User updatePassword(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	@Override
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}
	
	
}

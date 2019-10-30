package webapp.db.h2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import webapp.model.Role;
import webapp.model.RoleJPA;
import webapp.model.RoleMongo;
import webapp.model.User;
import webapp.model.UserJPA;
import webapp.model.UserMongo;
import webapp.service.RoleService;
import webapp.service.UserService;

@Component
public class InitDbH2 implements ApplicationRunner{

	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(userService.findAll().isEmpty()) {
			System.out.println("empty");
			
			RoleMongo adminRole = new RoleMongo("ADMIN");
			RoleMongo userRole = new RoleMongo("USER");
			
			roleService.create(adminRole);
			roleService.create(userRole);
			
			List<RoleMongo> roles = new ArrayList<RoleMongo>();
			
			roles.add(adminRole);
			UserMongo admin = new UserMongo();
			admin.setUsername("admin");
			admin.setEmail("admin@springapp.com");
			admin.setPassword("password");
			admin.setRoles(roles);
			userService.create(admin);
		}
	}

}

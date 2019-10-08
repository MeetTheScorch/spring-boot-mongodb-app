package webapp.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import webapp.model.Role;
import webapp.model.User;
import webapp.service.RoleService;
import webapp.service.UserService;

@Controller
public class DbPageController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value="/db")
    public String dbPage(Model model) {
		
		List<Role> rolesList = roleService.findAll();
		Collections.sort(rolesList, Role.nameComparator);
		model.addAttribute("roles", rolesList);
		
		List<User> usersList = userService.findAll();
		Collections.sort(usersList, User.nameComparator);
		model.addAttribute("users", usersList);
		
		return "/db/db";
	}
	
	//Role
	
	@PostMapping(path = "/db/role/create")
	public String createRole(Role role) {
		@SuppressWarnings("unused")
		Role newRole = roleService.create(role);
		return "redirect:/db";
	}
	
	@GetMapping(path = "/db/role/find/{id}")
	@ResponseBody
	public Role findRole(@PathVariable("id") String id) {
		return roleService.findById(id);
	}
	
	@PostMapping(path = "/db/role/update")
    public String updateRoleWithModal(Role role) {
		roleService.update(role);
        return "redirect:/db";
    }

	@GetMapping(path = "/db/role/delete/{id}")
	public String deleteRole(@PathVariable("id") String id, HttpServletRequest request) {
		roleService.deleteById(id);
		return "redirect:/db";
	}
	
	//User
	
	@GetMapping(path = "/db/user/find/{id}")
	@ResponseBody
	public User findUser(@PathVariable("id") String id) {
		return userService.findById(id);
	}
	
	@PostMapping(path = "/db/user/update")
    public String updateUserWithModal(User user) {
		userService.update(user);
        return "redirect:/db";
    }
	
	@GetMapping(path = "/db/user/delete/{id}")
	public String deleteUser(@PathVariable("id") String id, HttpServletRequest request) {
		userService.deleteById(id);
		return "redirect:/db";
	}
}

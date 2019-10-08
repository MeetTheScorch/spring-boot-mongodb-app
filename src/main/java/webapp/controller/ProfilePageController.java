package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import webapp.model.User;
import webapp.service.UserService;
import webapp.validator.UpdateUserValidator;

@Controller
public class ProfilePageController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private UpdateUserValidator userValidator;

	@GetMapping(value = "/profile")
	public String profile(Model model, Authentication authentication, String error) {
		
		User currentUser = userService.findByUsername(authentication.getName());	
		model.addAttribute("name", currentUser.getUsername());
		model.addAttribute("email", currentUser.getEmail());
		
		model.addAttribute("userForm", new User());
		
		return "profile";
	}
	
	@PostMapping(value = "/profile")
	public String updateProfile(@ModelAttribute("userForm") User userForm,
			BindingResult bindingResult, Model model, Authentication authentication) {
		
		User currentUser = userService.findByUsername(authentication.getName());	
		model.addAttribute("name", currentUser.getUsername());
		model.addAttribute("email", currentUser.getEmail());
		
		userForm.setId(currentUser.getId());
		userForm.setUsername(currentUser.getUsername());
		userForm.setRoles(currentUser.getRoles());
		
		if(userForm.getEmail() == null) {
			userForm.setEmail(currentUser.getEmail());
		}
		
		if(userForm.getPassword() == null) {
			userForm.setEmail(currentUser.getPassword());
		}

		userValidator.validate(userForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "profile";
		}

		userService.update(userForm);
		
		return "profile";
	}
}

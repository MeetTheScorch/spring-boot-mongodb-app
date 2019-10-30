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
import webapp.model.UserJPA;
import webapp.model.UserMongo;
import webapp.service.UserService;
import webapp.validator.ProfileUpdateValidator;

@Controller
public class ProfilePageController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private ProfileUpdateValidator profileUpdateValidator;

	@GetMapping(value = "/profile")
	public String profile(Model model, Authentication authentication, String error) {
		
		UserMongo currentUser = userService.findByUsername(authentication.getName());	
		model.addAttribute("name", currentUser.getUsername());
		model.addAttribute("email", currentUser.getEmail());
		
		model.addAttribute("userForm", new UserJPA());
		
		return "profile";
	}
	
	@PostMapping(value = "/profile")
	public String updateProfile(@ModelAttribute("userForm") UserMongo userForm,
			BindingResult bindingResult, Model model, Authentication authentication) {
		
		UserMongo currentUser = userService.findByUsername(authentication.getName());	
		model.addAttribute("name", currentUser.getUsername());
		model.addAttribute("email", currentUser.getEmail());
		
		userForm.setId(currentUser.getId());
		userForm.setUsername(currentUser.getUsername());
		userForm.setRoles(currentUser.getRoles());
		userForm.setEmail(currentUser.getEmail());

		profileUpdateValidator.validate(userForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "profile";
		}

		userService.updatePassword(userForm);
		
		return "profile";
	}
}

package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import webapp.model.UserJPA;
import webapp.model.UserMongo;
import webapp.service.SecurityService;
import webapp.service.UserService;
import webapp.validator.UserCreateValidator;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserCreateValidator userCreateValidator;
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new UserMongo());
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registration(@ModelAttribute("userForm") UserMongo userForm, BindingResult bindingResult) {
		userCreateValidator.validate(userForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "registration";
		}
		userService.create(userForm);		
		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if(error != null) {
			model.addAttribute("error", "Your username and password is invalid.");
		}
		if(logout != null) {
			model.addAttribute("message", "You have been logged out successfully.");
		}
		
		return "login";
	}
}

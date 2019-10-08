package webapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import webapp.model.User;
import webapp.service.UserService;

@Component
public class UpdateUserValidator implements Validator {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User newUser = (User) target;
		
		if(!bCryptPasswordEncoder.matches(newUser.getPasswordOld(), userService.findByUsername(newUser.getUsername()).getPassword())) {
			errors.rejectValue("passwordOld", "Diff.userForm.passwordOld");
		}
		
		//test whether new password is different from old one
		if(bCryptPasswordEncoder.matches(newUser.getPassword(), userService.findByUsername(newUser.getUsername()).getPassword()) ) {
			errors.rejectValue("password", "Diff.userForm.password");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if(newUser.getPassword().length() < 8 || newUser.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password");
		}
		
		if(!newUser.getPasswordConfirm().equals(newUser.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if(user.getUsername().length() < 4 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if(userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		if(userService.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Duplicate.userForm.email");
		}
		*/
	}

}

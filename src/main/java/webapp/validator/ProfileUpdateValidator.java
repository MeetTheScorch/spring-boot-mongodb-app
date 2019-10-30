package webapp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import webapp.model.UserJPA;
import webapp.service.UserService;

@Component
public class ProfileUpdateValidator implements Validator {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserJPA.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserJPA newUser = (UserJPA) target;
		
		//check if user entered correct old password
		if(!bCryptPasswordEncoder.matches(newUser.getPasswordOld(), userService.findByUsername(newUser.getUsername()).getPassword())) {
			errors.rejectValue("passwordOld", "Diff.userForm.passwordOld");
		}
		
		//check if new password is different from old one
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
	}

}

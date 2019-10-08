package webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	
	@RequestMapping(value = "/" )
	public String homePage(Model model) {
		return "home";
	}
	
	@RequestMapping(value="/user")
    public String userPage() {
        return "user";
    }
	
	@RequestMapping(value="/admin")
    public String adminPage() {
        return "admin";
    }
	
	@RequestMapping("/403")
	public String error403() {
		return "/error/403";
	}
}

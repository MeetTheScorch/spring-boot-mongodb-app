package webapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import webapp.controller.AuthController;
import webapp.controller.DbPageController;
import webapp.controller.DefaultController;
import webapp.controller.ProfilePageController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllersSmokeTest {

	@Autowired
    private AuthController authController;
	
	@Autowired
	private DbPageController dbPageController;
	
    @Autowired
    private DefaultController defaultController;
    
    @Autowired
    private ProfilePageController profilePageCOntroller;

    @Test
    public void contexLoads() throws Exception {
    	assertThat(authController).isNotNull();
    	
    	assertThat(dbPageController).isNotNull();
    	
        assertThat(defaultController).isNotNull();
        
        assertThat(profilePageCOntroller).isNotNull();
    }
}
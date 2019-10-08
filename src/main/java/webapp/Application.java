package webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	//@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	public static void main(String [] args) {
		SpringApplication.run(Application.class, args);
		
		logger.info("App " + Application.class.getName() + " is up and running.");	
	}
}

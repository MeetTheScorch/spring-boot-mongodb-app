package webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/", "/login", "/registration", "/css/**", "/js/**")
			.permitAll()
		.and()
			.authorizeRequests()
			.antMatchers("/profile")
			.authenticated()
		.and()
			.authorizeRequests()
			.antMatchers("/user")
			.hasAuthority("USER")
		.and()
			.authorizeRequests()
			.antMatchers("/admin")
			.hasAuthority("ADMIN")
		.and()
			.authorizeRequests()
			.antMatchers("/db/**")
			.hasAnyAuthority("ADMIN")
		.and()
			.authorizeRequests()
			.antMatchers("/h2-console/**")
			.permitAll()
		.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.failureUrl("/login?error")  
		.and()
			.logout()
            .permitAll()
            .logoutSuccessUrl("/login?logout")
		.and()
	        .exceptionHandling()
	        .accessDeniedHandler(accessDeniedHandler);
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}

package com.brushmyskills.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.brushmyskills.security.repository.UserRepository;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	//private UserAuthService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	/*
	 * We are creating our application level security rest end points
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests()
		    .antMatchers("**/secure/**").authenticated()
		    .anyRequest().permitAll()
		    .and()
		    /*Currently we are using spring provided login page*/
		    .formLogin().permitAll();
		    /*If require our own custom login page*/
		    //.formLogin().loginPage("/customLoginPageUrl").permitAll();

	}

	/*
	 * Currently we are returning the same password without doing any encoding If
	 * required you can use any another password encoder implementation like Bcrypt
	 * etc
	 */
	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {

			@Override
			public boolean matches(CharSequence charSequence, String str) {
				return true;
			}

			@Override
			public String encode(CharSequence charSequence) {
				return charSequence.toString();
			}
		};
	}

}

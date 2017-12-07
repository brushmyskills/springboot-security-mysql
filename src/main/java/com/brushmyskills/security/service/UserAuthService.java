package com.brushmyskills.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brushmyskills.security.entity.User;
import com.brushmyskills.security.entity.UserAuthDetails;
import com.brushmyskills.security.repository.UserRepository;

/*
 * This UserAuthService class will be used by Spring core security
 */
@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> optonalUser = userRepository.findByName(username);

		optonalUser.orElseThrow(() -> new UsernameNotFoundException("User name not present"));
		/*
		 * UserAuthDetails userAuthDetails =optonalUser.map(user -> { return new
		 * UserAuthDetails(user); }).get();
		 * 
		 * return userAuthDetails;
		 */

		return optonalUser.map(UserAuthDetails::new).get();

	}

}

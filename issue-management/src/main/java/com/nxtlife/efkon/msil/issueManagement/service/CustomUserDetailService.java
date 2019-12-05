package com.nxtlife.efkon.msil.issueManagement.service;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!username.equalsIgnoreCase("efkon")) {
			throw new UsernameNotFoundException("User not found");
		}
		return new User("efkon", "abc123", Arrays.asList(new SimpleGrantedAuthority("ROLE_SUPPORT")));
	}

}

package com.nxtlife.efkon.msil.issueManagement.view;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.stream.Collectors;

import com.nxtlife.efkon.msil.issueManagement.entity.User;

public class CustomUserDetails extends User implements UserDetails {

	

	public CustomUserDetails(final User user) {
		super(user);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}

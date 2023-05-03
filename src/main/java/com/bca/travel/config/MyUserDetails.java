package com.bca.travel.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bca.travel.model.User;

public class MyUserDetails implements UserDetails{
	
	private final User myUser;
	
	public MyUserDetails(User user) {
		this.myUser=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority auth = new SimpleGrantedAuthority(myUser.getRole());
		return Arrays.asList(auth);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return myUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return myUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

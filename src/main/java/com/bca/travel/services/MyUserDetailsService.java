package com.bca.travel.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bca.travel.config.MyUserDetails;
import com.bca.travel.model.User;
import com.bca.travel.repository.UsersRepository;

public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired private UsersRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(username);
		if(user==null) {
			return null;	
		}
		else {
			return new MyUserDetails(user);
		}
		
	}

}

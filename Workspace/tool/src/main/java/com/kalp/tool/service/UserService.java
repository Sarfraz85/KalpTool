package com.kalp.tool.service;



import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kalp.tool.model.User;
import com.kalp.tool.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository user;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> usr=Optional.ofNullable(user.findByUsername(username));
		if(usr.isPresent())
		{
			return new org.springframework.security.core.userdetails.User(username, usr.get().getPassword(), new ArrayList<>());
		}
		return null;
	}

}

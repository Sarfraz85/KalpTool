package com.kalp.tool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kalp.tool.model.Client;
import com.kalp.tool.model.LoginReply;
import com.kalp.tool.model.User;
import com.kalp.tool.repository.ClientRepository;
import com.kalp.tool.repository.UserRepository;
import com.kalp.tool.util.jwtutil;

@Service
public class AdminService {
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	private jwtutil util;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private User user;

	public ResponseEntity<String> createClient(Client client) {
		try {
		Client res=clientRepo.save(client);
		return new ResponseEntity<String>("Client created successfully", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("Falied to create client",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public String register(User user) {
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		try {
			Optional<User> res = Optional.ofNullable(this.userRepo.findByUsername(user.getUsername()));
			if (res.isPresent()) {
				return "Username already exists";
			}
			user.setPassword(b.encode(user.getPassword()));
			this.userRepo.save(user);
			return "User created successfully";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public LoginReply getAuthenticated(String name, String pass) {

		Optional<User> u = Optional.ofNullable(userRepo.findByUsername(name));
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		if (u.isPresent()) {
			System.out.print(u.get().getPassword());
			System.out.print(b.encode(pass));
			if (b.matches(pass, u.get().getPassword())) {
				String jwt = util.generateToken(name);
				LoginReply log = new LoginReply();
				log.setUserName(name);
				log.setJwt(jwt);
				return log;
			}
			return null;
		}
		return null;
	}

}

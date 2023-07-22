package com.kalp.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalp.tool.model.Client;
import com.kalp.tool.model.Constants;
import com.kalp.tool.model.User;
import com.kalp.tool.repository.UserRepository;
import com.kalp.tool.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	UserRepository userRepo;
	
	Constants constants;
	
//	@GetMapping("/data")
//	public String getData(){
//		return "success "+getCurrentUserDetails().getUsername()+" "+getCurrentUserDetails().getAuthorities();
//	}
	
	
}

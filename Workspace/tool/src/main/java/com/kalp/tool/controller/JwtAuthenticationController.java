package com.kalp.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kalp.tool.model.Client;
import com.kalp.tool.model.Constants;
import com.kalp.tool.model.LoginReply;
import com.kalp.tool.model.User;
import com.kalp.tool.repository.UserRepository;
import com.kalp.tool.service.AdminService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
	AdminService adminService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AdminService serv;
	
	Constants constants;
    
	@PostMapping("/gettoken/login")
	public ResponseEntity<LoginReply> getToken(@RequestBody User user)
	{
		LoginReply res=serv.getAuthenticated(user.getUsername(),user.getPassword());
		if(res==null || res.isEmpty())
		{
			return new ResponseEntity<LoginReply>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<LoginReply>(res,HttpStatus.OK);
	}
    
	
    public User getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        	UserDetails userDet= (UserDetails) authentication.getPrincipal();
        	return userRepo.findByUsername(userDet.getUsername());
        } else {
            // Handle case when user is not authenticated or UserDetails is not available
            return null;
        }
    }
    
    @PostMapping("/gettoken/register")
	public String register(@RequestBody User user)
	{
		String res=serv.register(user);
		ResponseEntity<String> reply=null;
		switch(res)
		{
		case "User created successfully":
			reply=new ResponseEntity<String>(res, HttpStatus.OK);
			break;
		case "Username already exists":
			reply= new ResponseEntity<String>(res,HttpStatus.BAD_REQUEST);
			break;
		default:
			reply= new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new Gson().toJson(reply);
	}
	@PostMapping("/create-client")
	public ResponseEntity<String> createClient(@RequestBody Client client) {
		ResponseEntity<String> reply=null;
		if(getCurrentUserDetails().getRole().equals(Constants.ADMIN_ROLE)) {
			client.setAdmin(getCurrentUserDetails());
			return adminService.createClient(client);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getData")
	public String getData()
	{
		return "done"+getCurrentUserDetails().getRole()+" "+getCurrentUserDetails().getUsername();
	}
}

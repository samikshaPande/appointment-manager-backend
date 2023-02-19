package com.sam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sam.model.AuthRequest;
import com.sam.model.AuthResponse;
import com.sam.model.UserModel;
import com.sam.repository.UserRepository;
import com.sam.services.JwtUtil;
import com.sam.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
	
	@Autowired
    UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationManager manager;	

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping("/dashboard")
	private String testToken() {
		return "Welcome to dashboard" +SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@PostMapping("/sub")
	private String subscribeClient(@RequestBody UserModel request) {
		UserModel userModel = new UserModel();
		userModel.setUserName(request.getUserName());
		userModel.setPassword(passwordEncoder.encode(request.getPassword()));
		try {
			userRepository.save(userModel);
		} catch (Exception e) {
			return "Error" +e;
		}
		
		return "Successfully Subscribed ";
	}

	@PostMapping("/auth")
	private ResponseEntity<?> authenticateClient(@RequestBody AuthRequest request) {
		
		String userName = request.getUserName();
		UserModel userModel = new UserModel();
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (Exception e) {
			return ResponseEntity.ok("Bad credentials" + e);
		}
		UserDetails userDetail = userService.loadUserByUsername(userName);
		userModel = this.userRepository.findByUserName(userName);
		System.out.println(userDetail);
		
		String generatedToken = jwtUtil.generateToken(userDetail);
		return ResponseEntity.ok(new AuthResponse(generatedToken,userName,userModel.getRole()));
	}
}

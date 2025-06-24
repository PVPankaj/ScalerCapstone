package com.scaler.capstone.jobportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/users")
@Validated
public class UserController {

	@PostMapping("/register")
	public ResponseEntity<String>registerUser(@RequestBody  String userDTO){
		return new ResponseEntity<>("User Registered!", HttpStatus.CREATED);
	} 
	@PostMapping("/login")
	public ResponseEntity<String>loginUser(@RequestBody  String login){
		return new ResponseEntity<>("user-details", HttpStatus.OK);
	}
	@PostMapping("/changePass")
	public ResponseEntity<String>changePassword(@RequestBody  String loginDTO){
		return new ResponseEntity<>("user-details", HttpStatus.OK);
	}
	@PostMapping("/sendOtp/{email}")
	public ResponseEntity<String>sendOtp(@PathVariable  String email) throws Exception{
		return new ResponseEntity<>("user-details", HttpStatus.OK);
	}
	@GetMapping("/verifyOtp/{email}/{otp}")
	public ResponseEntity<String>verifyOtp(@PathVariable String email, @PathVariable String otp){
		return new ResponseEntity<>("OTP has been verified.", HttpStatus.ACCEPTED);
	}
}

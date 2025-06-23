package com.scaler.capstone.jobportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@PostMapping("/login")
	public ResponseEntity<?>createAuthenticationToken(){
        return ResponseEntity.ok("Auth Success!");
	}
}
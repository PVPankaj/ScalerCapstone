package com.scaler.capstone.jobportal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication-related endpoints such as login.
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    /**
     * Endpoint for user login. Returns a dummy success message for authentication.
     *
     * @return ResponseEntity with a success message.
     */
	@PostMapping("/login")
	public ResponseEntity<?>createAuthenticationToken(){
        return ResponseEntity.ok("Auth Success!");
	}
}
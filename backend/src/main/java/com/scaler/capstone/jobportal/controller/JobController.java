package com.scaler.capstone.jobportal.controller;

import java.lang.String;
import java.util.List;

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
@RequestMapping("/jobs")
@Validated
public class JobController {

	@PostMapping("/post")
	public ResponseEntity<String>postJob(@RequestBody String jobdata){
		return new ResponseEntity<>("job posting successful!", HttpStatus.CREATED);
	}
	
	
	@PostMapping("/postAll")
	public ResponseEntity<List<String>>postAllJob(@RequestBody List<String> Strings){
		
		return new ResponseEntity<>(List.of("job1","job2","job3"), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<String>>getAllJobs(){
		return new ResponseEntity<>(List.of("job1","job2","job3"), HttpStatus.OK);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<String>getJob(@PathVariable Long id){
		return new ResponseEntity<>("job1", HttpStatus.OK);
	}
	@PostMapping("apply/{id}")
	public ResponseEntity<String>applyJob(@PathVariable Long id){
		return new ResponseEntity<>("Applied Successfully", HttpStatus.OK);
	}
	@GetMapping("/postedBy/{id}")
	public ResponseEntity<List<String>>getJobsPostedBy(@PathVariable Long id){
		return new ResponseEntity<>(List.of("job1","job2","job3"), HttpStatus.OK);
	}
	
	@GetMapping("/history/{id}/{applicationStatus}")
	public ResponseEntity<List<String>>getHistory(@PathVariable Long id){
		return new ResponseEntity<>(List.of("job1","job2","job3"), HttpStatus.OK);
	}
	@PostMapping("/changeAppStatus")
	public ResponseEntity<String>changeAppStatus(String application){
		return new ResponseEntity<>("Status Chhanged Successfully", HttpStatus.OK);
	}
	
	
	
}

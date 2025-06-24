package com.scaler.capstone.jobportal.repository;

import com.scaler.capstone.jobportal.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, Long> {
	
}

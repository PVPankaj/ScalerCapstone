package com.scaler.capstone.jobportal.repository;

import com.scaler.capstone.jobportal.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}
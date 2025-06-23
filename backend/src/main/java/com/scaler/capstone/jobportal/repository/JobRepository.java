package com.scaler.capstone.jobportal.repository;

import com.scaler.capstone.jobportal.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JobRepository extends MongoRepository<Job, Long> {
}

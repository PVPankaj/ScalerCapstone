package com.scaler.capstone.jobportal.repository;

import com.scaler.capstone.jobportal.model.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OTPRepository extends MongoRepository<OTP, String> {
}

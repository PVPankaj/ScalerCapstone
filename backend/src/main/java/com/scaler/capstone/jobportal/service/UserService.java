package com.scaler.capstone.jobportal.service;

import com.scaler.capstone.jobportal.dto.request.LoginRequestDTO;
import com.scaler.capstone.jobportal.dto.request.UserRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ResponseMessageDTO;
import com.scaler.capstone.jobportal.dto.response.UserResponseDTO;
import com.scaler.capstone.jobportal.exception.JobPortalException;

public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO) throws JobPortalException;

    UserResponseDTO getUserByEmail(String email) throws JobPortalException;

    UserResponseDTO loginUser(LoginRequestDTO loginResponseDTO) throws JobPortalException;

    Boolean sendOTP(String email) throws Exception;

    Boolean verifyOtp(String email, String otp) throws JobPortalException;

    ResponseMessageDTO changePassword(LoginRequestDTO loginRequestDTO) throws JobPortalException;

}

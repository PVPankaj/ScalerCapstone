package com.scaler.capstone.jobportal.service;

import com.scaler.capstone.jobportal.dto.request.LoginRequestDTO;
import com.scaler.capstone.jobportal.dto.request.NotificationRequestDTO;
import com.scaler.capstone.jobportal.dto.request.UserRequestDTO;
import com.scaler.capstone.jobportal.dto.response.ResponseMessageDTO;
import com.scaler.capstone.jobportal.dto.response.UserResponseDTO;
import com.scaler.capstone.jobportal.exception.JobPortalException;
import com.scaler.capstone.jobportal.mapper.UserMapper;
import com.scaler.capstone.jobportal.model.OTP;
import com.scaler.capstone.jobportal.model.User;
import com.scaler.capstone.jobportal.repository.OTPRepository;
import com.scaler.capstone.jobportal.repository.UserRepository;
import com.scaler.capstone.jobportal.util.MongoUtil;
import com.scaler.capstone.jobportal.util.OTPUtil;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userDTO) throws JobPortalException {
        Optional<User> optional = userRepository.findByEmail(userDTO.getEmail());
        if (optional.isPresent())
            throw new JobPortalException("USER_FOUND");
        userDTO.setId(MongoUtil.getNextSequenceId("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setProfileId(profileService.createProfile(userDTO));
        User user = userRepository.save(userMapper.toModel(userDTO));
        user.setPassword(null);
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDTO loginUser(LoginRequestDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new JobPortalException("INVALID_CREDENTIALS");
        user.setPassword(null);
        return userMapper.toResponseDto(user);
    }

    @Override
    public Boolean sendOTP(String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm, true);
        message.setTo(email);
        message.setSubject("Your OTP Code");
        String generatedOtp = OTPUtil.generateOTP();
        OTP otp = new OTP(email, generatedOtp, LocalDateTime.now());
        otpRepository.save(otp);
        mailService.sendOtpEmail(email, "Your OTP Code", generatedOtp);
        return true;
    }


    @Override
    public Boolean verifyOtp(String email, String otp) throws JobPortalException {
        OTP otpEntity = otpRepository.findById(email).orElseThrow(() -> new JobPortalException("OTP_NOT_FOUND"));
        if (!otpEntity.getOtpCode().equals(otp)) throw new JobPortalException("OTP_INCORRECT");
        return true;
    }

    @Scheduled(fixedRate = 60000)
    public void removeExpiredOTPs() {
        LocalDateTime expiryTime = LocalDateTime.now().minusMinutes(5);
        List<OTP> expiredOTPs = otpRepository.findByCreationTimeBefore(expiryTime);
        if (!expiredOTPs.isEmpty()) {
            otpRepository.deleteAll(expiredOTPs);
            System.out.println("Removed " + expiredOTPs.size() + " expired OTPs");
        }
    }

    @Override
    public ResponseMessageDTO changePassword(LoginRequestDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        NotificationRequestDTO notification = new NotificationRequestDTO();
        notification.setUserId(user.getId());
        notification.setMessage("Password Reset Successfull");
        notification.setAction("Password Reset");
        notificationService.sendNotification(notification);
        return new ResponseMessageDTO("Password changed successfully.");
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) throws JobPortalException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        return userMapper.toResponseDto(user);
    }

}

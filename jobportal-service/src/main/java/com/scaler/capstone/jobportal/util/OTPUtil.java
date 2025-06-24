package com.scaler.capstone.jobportal.util;

import java.security.SecureRandom;

public class OTPUtil {
    public static String generateOTP() {
        StringBuilder otp = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 6; i++) {
            otp.append(secureRandom.nextInt(10));
        }
        return otp.toString();
    }
}

package com.scaler.capstone.jobportal.dto.response;

import com.scaler.capstone.jobportal.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private AccountType accountType;
    private Long profileId;
}

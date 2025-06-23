package com.scaler.capstone.jobportal.dto.request;

import com.scaler.capstone.jobportal.enums.AccountType;
import com.scaler.capstone.jobportal.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private AccountType accountType;
    private Long profileId;

    public User toEntity() {
        return new User(this.id, this.name, this.email, this.password, this.accountType, this.profileId);
    }

}

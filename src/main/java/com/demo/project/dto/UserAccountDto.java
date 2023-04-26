package com.demo.project.dto;

import com.demo.project.entity.UserAccountEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDto {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty
    private String role;

    public UserAccountDto(UserAccountEntity userAccountEntity) {
        this.firstName = userAccountEntity.getFirstName();
        this.lastName = userAccountEntity.getLastName();
        this.email = userAccountEntity.getEmail();
        this.password = userAccountEntity.getPassword();
        this.role = userAccountEntity.getRole().getName();
    }
}

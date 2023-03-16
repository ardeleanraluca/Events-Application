package com.demo.project.model;

import com.demo.project.util.UserType;
import jakarta.persistence.Column;
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
public class UserModel {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String dateOfBirth;

    @NotEmpty
    private String city;

    @NotEmpty
    private String county;

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty(message = "Password should not be empty")
    private String password;

}

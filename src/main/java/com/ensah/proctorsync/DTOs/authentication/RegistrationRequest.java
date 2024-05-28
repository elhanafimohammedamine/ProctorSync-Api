package com.ensah.proctorsync.DTOs.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    @NotBlank
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phone;
    private String password;
}

package com.ensah.proctorsync.DTOs.administrator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAdministratorRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}

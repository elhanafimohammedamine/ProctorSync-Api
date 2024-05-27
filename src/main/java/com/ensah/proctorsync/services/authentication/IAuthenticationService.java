package com.ensah.proctorsync.services.authentication;

import com.ensah.proctorsync.DTOs.authentication.AuthenticationRequest;
import com.ensah.proctorsync.DTOs.authentication.AuthenticationResponse;
import com.ensah.proctorsync.DTOs.authentication.RegistrationRequest;

public interface IAuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    AuthenticationResponse register(RegistrationRequest registrationRequest);
}

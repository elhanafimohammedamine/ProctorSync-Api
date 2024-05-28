package com.ensah.proctorsync.services.authentication;

import com.ensah.proctorsync.DTOs.authentication.AuthenticationRequest;
import com.ensah.proctorsync.DTOs.authentication.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}

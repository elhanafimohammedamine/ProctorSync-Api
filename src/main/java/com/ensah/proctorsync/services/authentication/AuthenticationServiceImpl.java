package com.ensah.proctorsync.services.authentication;

import com.ensah.proctorsync.DTOs.authentication.AuthenticationRequest;
import com.ensah.proctorsync.DTOs.authentication.AuthenticationResponse;
import com.ensah.proctorsync.DTOs.authentication.RegistrationRequest;
import com.ensah.proctorsync.config.JwtService;
import com.ensah.proctorsync.entities.User;
import com.ensah.proctorsync.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationRequest.getEmail(),
                                authenticationRequest.getPassword()
                        )
                );

        User user = userService.loadUserByEmail(authenticationRequest.getEmail());
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse register(RegistrationRequest registrationRequest) {

        User newUser = User.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phone(registrationRequest.getPhone())
                .build();

        User savedUser = userService.saveUser(newUser);
        String accessToken = jwtService.generateToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);

        return AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}

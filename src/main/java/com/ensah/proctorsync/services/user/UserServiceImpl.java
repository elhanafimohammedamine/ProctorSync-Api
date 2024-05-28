package com.ensah.proctorsync.services.user;

import com.ensah.proctorsync.entities.User;
import com.ensah.proctorsync.exception.NotFoundException;
import com.ensah.proctorsync.repositories.user.IUserRepository;

import com.ensah.proctorsync.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User loadUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("USER WITH EMAIL " + email + "DOES NOT EXIST!");
                            LOGGER.error("Error while loading user by email {} ", email ,notFoundException);
                            return notFoundException;
                        });
    }
}
package com.ensah.proctorsync.services.user;

import com.ensah.proctorsync.entities.User;
import com.ensah.proctorsync.exception.NotFoundException;
import com.ensah.proctorsync.exception.AlreadyExistException;
import com.ensah.proctorsync.repositories.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public User saveUser(User newUser) {

        // check whether the user is already exist
        Optional<User> user = userRepository
                .findByEmail(newUser.getEmail());

        if (user.isPresent()) {
            AlreadyExistException alreadyExistException = new AlreadyExistException("USER WITH EMAIL " + user.get().getEmail() + "ALREADY EXIST!");
            LOGGER.error("Error while saving new user with email {}", user.get().getEmail(), alreadyExistException);
            throw alreadyExistException;
        }

        return userRepository.save(newUser);
    }
}

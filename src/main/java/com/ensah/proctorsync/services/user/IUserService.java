package com.ensah.proctorsync.services.user;

import com.ensah.proctorsync.entities.User;

import java.util.Optional;

public interface IUserService {
     User loadUserByEmail(String email);
     User saveUser(User user);

}

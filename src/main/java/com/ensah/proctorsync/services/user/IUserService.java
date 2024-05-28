package com.ensah.proctorsync.services.user;

import com.ensah.proctorsync.entities.User;

public interface IUserService {
     User loadUserByEmail(String email);

}

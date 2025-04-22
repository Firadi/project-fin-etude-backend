package com.project_technique.project_technique.services;

import com.project_technique.project_technique.models.User;
import com.project_technique.project_technique.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User createUser(User user){

        System.out.println(user);
        return userRepo.save(user);
    }

}

package com.project_technique.project_technique.controllers;

import com.project_technique.project_technique.models.User;
import com.project_technique.project_technique.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;



}

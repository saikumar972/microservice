package com.acc.user.controller;

import com.acc.user.dto.UserRequest;
import com.acc.user.dto.UserResponse;
import com.acc.user.entity.Userentity;
import com.acc.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/add")
    public UserResponse addUser(@Valid  @RequestBody UserRequest userRequest){
        return service.adduser(userRequest);
    }

    @GetMapping("get/{id}")
    public UserResponse getUserById(@PathVariable int id){
        return service.getUserById(id);
    }

    @PutMapping("/update/{id}/{amount}")
    public UserResponse updateUser(@PathVariable int id,@PathVariable double amount){
        return service.updateAmount(id,amount);
    }

    @GetMapping("/getAll")
    public List<UserResponse> getUserData(){
        return service.getAllusers();
    }
}

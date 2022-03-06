package com.amiroshnikov.PearStore.controller;

import com.amiroshnikov.PearStore.dto.ResponseDto;
import com.amiroshnikov.PearStore.dto.user.SighInDto;
import com.amiroshnikov.PearStore.dto.user.SighInResponseDto;
import com.amiroshnikov.PearStore.dto.user.SighupDto;
import com.amiroshnikov.PearStore.exception.AuthenticationException;
import com.amiroshnikov.PearStore.model.User;
import com.amiroshnikov.PearStore.repository.UserRepository;
import com.amiroshnikov.PearStore.service.AuthenticationService;
import com.amiroshnikov.PearStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @GetMapping("/allUsers")
    public List<User> findAllUsers(@RequestParam("token") String token) throws AuthenticationException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseDto sighup(@RequestBody SighupDto sighupDto) {
        return userService.sighup(sighupDto);
    }

    @PostMapping("/signin")
    public SighInResponseDto sighIn(@RequestBody SighInDto sighInDto) {
        return userService.sighIn(sighInDto);
    }
}

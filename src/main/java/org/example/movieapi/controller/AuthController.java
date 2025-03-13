package org.example.movieapi.controller;

import org.example.movieapi.dtos.user.LoginDto;
import org.example.movieapi.dtos.user.RegisterDto;
import org.example.movieapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> register(@ModelAttribute RegisterDto registerDto) {
        return ResponseEntity.ok(userService.registerUser(registerDto));
    }

    @PostMapping(value = "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> login(@ModelAttribute LoginDto loginDto) {
        return ResponseEntity.ok(userService.loginUser(loginDto));
    }
}

package org.example.movieapi.service;

import org.example.movieapi.dtos.user.LoginDto;
import org.example.movieapi.dtos.user.RegisterDto;

public interface UserService {
    String registerUser(RegisterDto registerDto);
    String loginUser(LoginDto loginDto);
}

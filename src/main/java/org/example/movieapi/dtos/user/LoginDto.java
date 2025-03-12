package org.example.movieapi.dtos.user;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}

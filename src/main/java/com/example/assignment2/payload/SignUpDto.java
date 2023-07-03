package com.example.assignment2.payload;

import io.swagger.annotations.Api;
import lombok.Data;
@Api(value = "SignUp model information")


@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}

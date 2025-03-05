package com.example.yoga.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LoginForm {
    private String email;
    private String password;
    private String userRole;
}

package com.example.yoga.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.yoga.dto.LoginForm;
import com.example.yoga.dto.SignUpForm;
import com.example.yoga.model.User;
import com.example.yoga.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int getUserById(LoginForm loginForm) {
        int k = findByEmail(loginForm.getEmail());
        System.out.println(k);
        if(k == 0)
            return 1;

        User user = userRepository.findByEmail(loginForm.getEmail()).get();
        if(user.getEmail().equals(loginForm.getEmail()) && user.getPassword().equals(loginForm.getPassword()) && user.getUserRole().equals("User"))
            return 2;
        else if(user.getEmail().equals(loginForm.getEmail()) && user.getPassword().equals(loginForm.getPassword()) && user.getUserRole().equals("Admin"))
            return 3;
        else
            return 4;
    }

    // public User saveUser(SignUpForm signUpForm) {
    //     User user = new User(0, signUpForm.getUsername(), signUpForm.getEmail(), signUpForm.getPassword(), signUpForm.getUserRole());
    //     return userRepository.save(user);
    // }
     public User saveUser(SignUpForm signUpForm) {
        User user = new User(0, signUpForm.getUserName(), signUpForm.getEmail(), signUpForm.getPassword(), signUpForm.getUserRole());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public List<User>getallUsers()
    {
        return userRepository.findAll();
    }
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public int findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(!user.isEmpty())
            return 1;
        return 0;
    }
    

}

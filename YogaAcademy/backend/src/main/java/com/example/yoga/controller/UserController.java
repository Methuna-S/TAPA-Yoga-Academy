package com.example.yoga.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.yoga.dto.AuthRequest;
import com.example.yoga.dto.LoginForm;
import com.example.yoga.dto.SignUpForm;
import com.example.yoga.model.User;
import com.example.yoga.repository.UserRepo;
import com.example.yoga.service.JwtService;
import com.example.yoga.service.UserService;
@CrossOrigin(origins ="http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
     @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/check/{email}")
    public ResponseEntity<?> getUsers(@PathVariable String email) {
        try {
            return ResponseEntity.status(200).body(userService.findByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
        }
    }
    
    
    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // @PostMapping("/login")
    // public ResponseEntity<?> getUserById(@RequestBody LoginForm loginForm) {
    //     try {
    //         int userStatus = userService.getUserById(loginForm);
    //         return ResponseEntity.status(200).body(userStatus);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("Internal Server Error: " + e.getMessage());
    //     }
    // }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) {
        try {
            User user = userRepo.findByEmail(authRequest.getUserName()).orElse(null);
            System.out.println(authRequest.getUserName());
            System.out.println(authRequest.getPassword());
            if (user == null) {
                return ResponseEntity.status(404).body("User not found!");  
            }
            String password = user.getPassword();
            // System.out.println(authRequest.getPassword() + " " + password);
            try{
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
                if (authentication.isAuthenticated() && passwordEncoder.matches(authRequest.getPassword(), password)) {
                    String token = jwtService.generateToken(authRequest.getUserName());
                    return ResponseEntity.ok(token);
                } else {
                    // System.out.println("Hello");
                    return ResponseEntity.status(401).body("Invalid credentials!");  // Return 401 for invalid credentials
                }
            }
            catch(Exception e){
                return ResponseEntity.status(401).body("Invalid credentials 1 !");  // Return 401 for invalid credentials

            }
        } 
        catch (Exception e) {
            return ResponseEntity.status(500).body("There was an error processing your request!");
        }
    }


    @PostMapping("/registerUser")
    public ResponseEntity<User> createUser(@RequestBody SignUpForm signUpForm) {
        try {
            User user = userService.saveUser(signUpForm);
            return ResponseEntity.status(200).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}

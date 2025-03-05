package com.example.yoga.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yoga.model.User;
import java.util.List;



@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
}
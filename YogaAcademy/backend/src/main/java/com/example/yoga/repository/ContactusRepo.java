package com.example.yoga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yoga.model.ContactUs;

@Repository
public interface ContactusRepo extends JpaRepository<ContactUs,Integer> {
    
}

package com.example.yoga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yoga.model.BuyForm;

@Repository
public interface BuyFormRepo extends JpaRepository<BuyForm,Integer> {
    
}

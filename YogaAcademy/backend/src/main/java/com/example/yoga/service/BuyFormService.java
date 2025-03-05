package com.example.yoga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yoga.model.BuyForm;
import com.example.yoga.repository.BuyFormRepo;

@Service
public class BuyFormService {
    
    @Autowired
    private BuyFormRepo buyFormRepo;

    public List<BuyForm> getAllPlans() {
        return buyFormRepo.findAll();
    }

    public Optional<BuyForm> getPlanById(int id) {
        return buyFormRepo.findById(id);
    }

    public BuyForm savePlan(BuyForm plan) {
        return buyFormRepo.save(plan);
    }

    public void deletePlan(int id) {
        buyFormRepo.deleteById(id);
    }
}

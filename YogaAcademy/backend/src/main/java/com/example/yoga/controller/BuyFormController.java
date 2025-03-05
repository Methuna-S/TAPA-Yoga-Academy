package com.example.yoga.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.yoga.model.BuyForm;
import com.example.yoga.service.BuyFormService;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/buyForm")

public class BuyFormController {
    @Autowired
    private BuyFormService plansService;

    @GetMapping
    public ResponseEntity<List<BuyForm>> getAllPlans() {
        List<BuyForm> buyForm = plansService.getAllPlans();
        return new ResponseEntity<>(buyForm, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuyForm> getPlanById(@PathVariable int id) {
        Optional<BuyForm> plan = plansService.getPlanById(id);
        return plan.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BuyForm> createPlan(@RequestBody BuyForm plan) {
        BuyForm savedPlan = plansService.savePlan(plan);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuyForm> updatePlan(@PathVariable int id, @RequestBody BuyForm plan) {
        Optional<BuyForm> existingPlan = plansService.getPlanById(id);
        if (existingPlan.isPresent()) {
            plan.setId(id);
            BuyForm updatedPlan = plansService.savePlan(plan);
            return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable int id) {
        Optional<BuyForm> existingPlan = plansService.getPlanById(id);
        if (existingPlan.isPresent()) {
            plansService.deletePlan(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.example.yoga.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yoga.model.BankAccount;
import com.example.yoga.model.BankAccount;
import com.example.yoga.repository.BankRepository;



@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public Optional<BankAccount> validateUpiId(String upiId) {
        return bankRepository.findByUpiId(upiId);
     }

}

package com.example.yoga.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.yoga.model.BankAccount;
import com.example.yoga.model.BankAccount;

import java.util.Optional;

    
@Repository
public interface BankRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByUpiId(String upiId);
}


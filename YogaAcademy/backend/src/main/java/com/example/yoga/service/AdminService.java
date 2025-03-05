package com.example.yoga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yoga.model.Admin;
import com.example.yoga.repository.AdminRepo;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepo adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }

    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}

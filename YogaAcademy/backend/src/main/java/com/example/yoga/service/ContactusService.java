package com.example.yoga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yoga.model.ContactUs;
import com.example.yoga.repository.ContactusRepo;

@Service
public class ContactusService {
    
    @Autowired
    private ContactusRepo contactUsRepository;

    public List<ContactUs> getAllContacts() {
        return contactUsRepository.findAll();
    }

    public Optional<ContactUs> getContactById(int id) {
        return contactUsRepository.findById(id);
    }

    public ContactUs saveContact(ContactUs contact) {
        return contactUsRepository.save(contact);
    }

    public void deleteContact(int id) {
        contactUsRepository.deleteById(id);
    }
}

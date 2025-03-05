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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yoga.model.ContactUs;
import com.example.yoga.service.ContactusService;

@RestController
@RequestMapping("/api/contacts")
public class ContactusController {
    @Autowired
    private ContactusService contactUsService;

    @GetMapping
    public ResponseEntity<List<ContactUs>> getAllContacts() {
        List<ContactUs> contacts = contactUsService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactUs> getContactById(@PathVariable int id) {
        Optional<ContactUs> contact = contactUsService.getContactById(id);
        return contact.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ContactUs> createContact(@RequestBody ContactUs contact) {
        ContactUs savedContact = contactUsService.saveContact(contact);
        return new ResponseEntity<>(savedContact, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactUs> updateContact(@PathVariable int id, @RequestBody ContactUs contact) {
        Optional<ContactUs> existingContact = contactUsService.getContactById(id);
        if (existingContact.isPresent()) {
            contact.setId(id);
            ContactUs updatedContact = contactUsService.saveContact(contact);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        Optional<ContactUs> existingContact = contactUsService.getContactById(id);
        if (existingContact.isPresent()) {
            contactUsService.deleteContact(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

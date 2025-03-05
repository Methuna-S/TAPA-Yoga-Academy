package com.example.yoga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.yoga.service.BankService;
@RestController
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping("/payment")
    public String paymentForm() {
        return "paymentForm";
    }

    @PostMapping("/validate")
    public String validatePayment(@RequestParam("upiId") String upiId, Model model) {
        if (bankService.validateUpiId(upiId).isPresent()) {
            return "redirect:/purchasedPlan";
        } else {
            model.addAttribute("error", "Invalid UPI ID. Please try again.");
            return "paymentForm";
        }
    }

    @GetMapping("/purchasedPlan")
    public String purchasedPlan() {
        return "purchasedPlan";
    }

}

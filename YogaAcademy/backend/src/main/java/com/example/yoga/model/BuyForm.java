package com.example.yoga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuyForm {
    @Id
    private int id;
    private String name;
    private int age;
    private String gender;
    private int phoneNumber;
    private String email;
    private String upiId;
    private String selectTrainer;
    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private User user;

}

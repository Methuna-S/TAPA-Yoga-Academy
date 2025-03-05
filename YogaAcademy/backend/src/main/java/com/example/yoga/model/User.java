package com.example.yoga.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String email;
    private String password;
    private String userRole;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<Video> videos;

    // @ManyToOne
    // @JoinColumn(name = "admin_id")
    // private Admin admin;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<BuyForm> buyForms;
}

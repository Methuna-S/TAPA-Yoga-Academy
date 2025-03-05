package com.example.yoga.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.SecondaryRow;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vId;
    private String title;
    private String url;
    private String description;
    private Date created=Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));


    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private User user;
}

package com.example.yoga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.yoga.model.Video;



public interface VideoRepository extends JpaRepository<Video,Long>
{
    
}
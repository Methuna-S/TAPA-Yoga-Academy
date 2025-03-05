package com.example.yoga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.yoga.model.Video;
import com.example.yoga.repository.VideoRepository;

@Service
public class VideoService 
{
     private final VideoRepository videoRepository;

     @Autowired
     public VideoService(VideoRepository videoRepository)
     {
        this.videoRepository=videoRepository;
     }
     public Video createVideoLink(Video videoLink) {
        return videoRepository.save(videoLink);
    }

    public List<Video> getAllVideoLinks() {
        return videoRepository.findAll();
    }

    public Optional<Video> getVideoLinkById(Long id) {
        return videoRepository.findById(id);
    }

    public Video updateVideoLink(Long id, Video updatedVideoLink) {
        Optional<Video> existingVideoLink = videoRepository.findById(id);
        if (existingVideoLink.isPresent()) {
            Video videoLink = existingVideoLink.get();
            videoLink.setTitle(updatedVideoLink.getTitle());
            videoLink.setUrl(updatedVideoLink.getUrl());
            videoLink.setDescription(updatedVideoLink.getDescription());
            return videoRepository.save(videoLink);
        }
        return null;
    }

    public void deleteVideoLink(Long id) 
    {
        videoRepository.deleteById(id);
    }
}

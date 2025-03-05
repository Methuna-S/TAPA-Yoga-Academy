package com.example.yoga.controller;

import java.util.List;

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

import com.example.yoga.model.Video;
import com.example.yoga.service.VideoService;

@RestController
@RequestMapping("/api/videolinks")
public class VideoController 
{
   private final VideoService videoService;

   public VideoController (VideoService videoService)
   {
    this.videoService=videoService;
   }
   @PostMapping
    public ResponseEntity<Video> createVideoLink(@RequestBody Video videoLink) {
        Video createdVideoLink = videoService.createVideoLink(videoLink);
        return new ResponseEntity<>(createdVideoLink, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideoLinks() {
        List<Video> videoLinks = videoService.getAllVideoLinks();
        return new ResponseEntity<>(videoLinks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoLinkById(@PathVariable Long id) {
        return videoService.getVideoLinkById(id)
                .map(videoLink -> new ResponseEntity<>(videoLink, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> updateVideoLink(@PathVariable Long id, @RequestBody Video videoLink) {
        Video updatedVideoLink = videoService.updateVideoLink(id, videoLink);
        if (updatedVideoLink != null) {
            return new ResponseEntity<>(updatedVideoLink, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideoLink(@PathVariable Long id) {
        videoService.deleteVideoLink(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
    


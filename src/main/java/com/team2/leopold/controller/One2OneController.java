package com.team2.leopold.controller;

import com.team2.leopold.repository.One2OneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class One2OneController {
    private One2OneRepository repository;

    @Autowired
    public One2OneController(One2OneRepository repository){
        this.repository = repository;
    }

}

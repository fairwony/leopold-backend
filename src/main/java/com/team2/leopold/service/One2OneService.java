package com.team2.leopold.service;

import com.team2.leopold.entity.One2One;
import com.team2.leopold.repository.One2OneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class One2OneService {
    private One2OneRepository repository;

    @Autowired
    public One2OneService(One2OneRepository repository){
        this.repository = repository;
    }

    @Transactional
    public void insertPost(One2One one2One) {
        repository.insertPosts(one2One);
    }

}

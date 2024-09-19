package com.team2.leopold.service;

import com.team2.leopold.entity.One2One;
import com.team2.leopold.repository.One2OneRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class One2OneService {
    private One2OneRepository repository;

    @Autowired
    public One2OneService(One2OneRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void insertPost(One2One one2One) {
        repository.save(one2One);
    }

    public Optional<One2One> findOne2One(Integer uid) throws NoResultException {
        return repository.findById(uid);
    }
}
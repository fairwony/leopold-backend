package com.team2.leopold.service;

import com.team2.leopold.entity.Notice;
import com.team2.leopold.entity.One2One;
import com.team2.leopold.entity.User;
import com.team2.leopold.repository.One2OneRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class One2OneService {
    private One2OneRepository repository;

    @Autowired
    public One2OneService(One2OneRepository repository) {
        this.repository = repository;
    }

    // 1대1 문의 작성
    @Transactional
    public void insertOne2One(One2One one2One) {
        repository.save(one2One);
    }

    // 1대1 문의 상세조회
    public One2One findOne2One(int uid) {
        Optional<One2One> foundOne2One = repository.findById(uid);
        if (foundOne2One.isPresent()) {
            return foundOne2One.get();
        }
        return null;
    }

}
package com.team2.leopold.service;

import com.team2.leopold.dto.ResponseAllOne2OneDto;
import com.team2.leopold.entity.One2One;
import com.team2.leopold.repository.One2OneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    // 1대1 문의 상세 조회
    public One2One findOne2One(int uid) {
        Optional<One2One> foundOne2One = repository.findById(uid);
        if (foundOne2One.isPresent()) {
            return foundOne2One.get();
        }
        return null;
    }

    // 1대1 문의 전체 조회


}
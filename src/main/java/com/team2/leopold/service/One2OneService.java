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
        //repository.insertPosts(one2One);
        //코드에 문제가 있어서 주석처리 했습니다.
        //엔티티로 데이터베이스에 정보를 추가하는 메소드는 save라는 기본적으로 제공해주는 메소드가 있습니다.
        //UserService 파일을 참고해보세요.
    }

}

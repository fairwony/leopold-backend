package com.team2.leopold.controller;

import com.team2.leopold.dto.RequestOne2OneDto;
import com.team2.leopold.dto.ResponseNoticeDto;
import com.team2.leopold.dto.ResponseOne2OneDto;
import com.team2.leopold.entity.One2One;
import com.team2.leopold.entity.User;
import com.team2.leopold.repository.One2OneRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class One2OneController {
    private One2OneRepository repository;

    @Autowired
    public One2OneController(One2OneRepository repository) {
        this.repository = repository;
    }

    // 게시글 작성
    @PostMapping("/one2one/write")
    public ResponseEntity<?> writeOne2One(@RequestBody RequestOne2OneDto requestOne2OneDto, HttpServletRequest request){
        if(requestOne2OneDto.getTitle() == null || requestOne2OneDto.getContent() == null
        || requestOne2OneDto.getEmail() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터가 누락되었습니다.");

        HttpSession session = request.getSession(false);
        if (session == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않습니다.");

        User user = new User();
        user.setUid((Integer)session.getAttribute("userUid"));

        One2One one2One = new One2One();
        one2One.setTitle(requestOne2OneDto.getTitle());
        one2One.setContent(requestOne2OneDto.getContent());
        one2One.setEmail(requestOne2OneDto.getEmail());
        one2One.setUser(user);

        repository.save(one2One);
        return ResponseEntity.status(HttpStatus.OK).body("게시글 작성 완료!");
    }


}

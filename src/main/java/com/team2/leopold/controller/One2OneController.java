package com.team2.leopold.controller;

import com.team2.leopold.dto.RequestOne2OneDto;
import com.team2.leopold.dto.ResponseOne2OneDto;
import com.team2.leopold.entity.One2One;
import com.team2.leopold.entity.User;
import com.team2.leopold.repository.One2OneRepository;
import com.team2.leopold.service.One2OneService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class One2OneController {
    private One2OneRepository repository;

    private final One2OneService service;

    @Autowired
    public One2OneController(One2OneRepository repository, One2OneService service) {
        this.repository = repository;
        this.service = service;
    }

    // 1대1 문의 작성
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

        service.insertOne2One(one2One);
        return ResponseEntity.status(HttpStatus.OK).body("게시글 작성 완료!");
    }

    // 1대1 문의 조회
    @GetMapping("/one2one/{uid}")
    public ResponseEntity<?> findOne2One(@PathVariable int uid){
        try {
            One2One one2One = service.findOne2One(uid);
            ResponseOne2OneDto responseOne2OneDto = new ResponseOne2OneDto(
                    one2One.getUid(),
                    one2One.getTitle(),
                    one2One.getContent(),
                    one2One.getUser().getName(),
                    one2One.getWriteDate(),
                    one2One.getAnswerYn()
            );
            return ResponseEntity.status(HttpStatus.OK).body(responseOne2OneDto);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }
    }

    // 1대1 문의 삭제
    @DeleteMapping("/one2one/delete/{uid}")
    public ResponseEntity<?> deleteOne2One(@PathVariable int uid, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않습니다.");

        Optional<One2One> optionalOne2One = repository.findById(uid);
        if(optionalOne2One.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 게시글을 찾을 수 없습니다.");
        }
        One2One foundOne2One = optionalOne2One.get();

        if (!foundOne2One.getUser().getUid().equals((Integer) session.getAttribute("userUid"))){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("본인이 작성한 게시물만 삭제할 수 있습니다.");
        }

        foundOne2One.setDeleteYn("y");
        foundOne2One.setDeleteDate(LocalDateTime.now());
        repository.save(foundOne2One);
        return ResponseEntity.status(HttpStatus.OK).body("게시글 삭제 완료");
    }
}

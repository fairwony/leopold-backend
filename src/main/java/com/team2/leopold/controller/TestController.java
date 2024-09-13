package com.team2.leopold.controller;

import com.team2.leopold.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<?> test(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("로그인되어 있습니다.");
    }

    @GetMapping("/test/info")
    public ResponseEntity<?> testInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않습니다.");
        }

        User user = (User) session.getAttribute("user");
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}

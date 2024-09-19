package com.team2.leopold.controller;

import com.team2.leopold.dto.RequestJoinDto;
import com.team2.leopold.dto.RequestLoginDto;
import com.team2.leopold.entity.User;
import com.team2.leopold.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /* 회원가입 */
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody RequestJoinDto dto) {
        if (dto.getId().isEmpty() || dto.getPassword().isEmpty() || dto.getName().isEmpty() || dto.getZipcode().isEmpty() || dto.getAddress().isEmpty() || dto.getPhone().isEmpty() || dto.getEmail().isEmpty() || dto.getAgreeEmailYn().isEmpty() || dto.getAgreeSmsYn().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("필수 정보 누락");

        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setZipcode(dto.getZipcode());
        user.setAddress(dto.getAddress());
        user.setAddressDetail(dto.getAddressDetail());
        user.setPhoneAlt(dto.getPhoneAlt());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setAgreeEmailYn(dto.getAgreeEmailYn());
        user.setAgreeSmsYn(dto.getAgreeSmsYn());

        try {
            service.join(user);
            return ResponseEntity.status(HttpStatus.OK).body("회원가입 완료!");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 아이디");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    /* 로그인 */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestLoginDto dto, HttpServletRequest request) {
        if (dto.getId().isEmpty() || dto.getPassword().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("필수 정보 누락");

        User user = new User();
        user.setId(dto.getId());
        user.setPassword(dto.getPassword());

        try {
            User foundUser = service.login(user);

            HttpSession session = request.getSession();
            session.setAttribute("user", foundUser);
            session.setAttribute("userUid", foundUser.getUid());

            return ResponseEntity.status(HttpStatus.OK).body("로그인 완료!");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 올바르지 않음");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    /* 로그아웃 */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("세션이 존재하지 않음");

        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃 완료!");
    }
}
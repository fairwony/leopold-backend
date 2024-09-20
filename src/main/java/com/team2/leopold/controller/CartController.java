package com.team2.leopold.controller;

import com.team2.leopold.dto.RequestCartDto;
import com.team2.leopold.entity.User;
import com.team2.leopold.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    private CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    /* 장바구니 등록 */
    @PostMapping("/cart")
    public ResponseEntity<?> insertCart(@RequestBody RequestCartDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않음");

        User user = (User) session.getAttribute("user");
        try {
            service.insertCart(dto.getCategory2(), dto.getCategory3(), dto.getCategory4(), dto.getCategory5(), dto.getQuantity(), user);
            return ResponseEntity.status(HttpStatus.OK).body("장바구니 등록 완료!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 상품을 찾을 수 없음");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }
}
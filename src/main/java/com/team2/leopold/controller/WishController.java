package com.team2.leopold.controller;

import com.team2.leopold.entity.Cart;
import com.team2.leopold.entity.Wish;
import com.team2.leopold.service.WishService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishController {
    private WishService wishService;

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    /* 구매 대기 상품 생성 */
    @PostMapping("/wish")
    public ResponseEntity<?> insertWish(@RequestParam(name = "cartUid") Integer cartUid, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않음.(생성)");

        try {
            wishService.insertWish(cartUid, (Integer) session.getAttribute("userUid"));
            return ResponseEntity.status(HttpStatus.OK).body("구매 대기 상품 생성 완료!");
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 장바구니를 찾을 수 없음");
        }
    }

    /* 구매 대기 상품 초기화 */
    @DeleteMapping("/wish/delete")
    public ResponseEntity<?> deleteWish(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않음.(초기화)");
        wishService.deleteWish((Integer) session.getAttribute("userUid"));
        return ResponseEntity.status(HttpStatus.OK).body("구매 상품 초기화 완료!");
    }

    /* 구매 대기 상품 목록 */
    @GetMapping("/wish")
    public ResponseEntity<?> findWish(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않음.(목록)");

        List<Wish> wishList = wishService.findWishList((Integer) session.getAttribute("userUid"));
        return ResponseEntity.status(HttpStatus.OK).body(wishList);
    }
}

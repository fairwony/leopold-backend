package com.team2.leopold.controller;

import com.team2.leopold.dto.RequestReviewDto;
import com.team2.leopold.dto.ResponseOne2OneDto;
import com.team2.leopold.dto.ResponseReviewDto;
import com.team2.leopold.entity.Review;
import com.team2.leopold.entity.User;
import com.team2.leopold.repository.ReviewRepository;
import com.team2.leopold.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    private ReviewRepository reviewRepository;

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, ReviewService reviewService){
        this.reviewRepository = reviewRepository;
        this.reviewService = reviewService;
    }

    // 리뷰 작성
    @PostMapping("/review/write")
    public ResponseEntity<?> writeReview(@RequestBody RequestReviewDto requestReviewDto, HttpServletRequest request){
        if(requestReviewDto.getTitle() == null || requestReviewDto.getContent() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터가 누락되었습니다.");

        HttpSession session = request.getSession(false);
        if (session == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않습니다.");

        User user = new User();
        user.setUid((Integer)session.getAttribute("userUid"));

        Review review = new Review();
        review.setTitle(requestReviewDto.getTitle());
        review.setContent(requestReviewDto.getContent());
        review.setVideoUrl(requestReviewDto.getVideoUrl());
        review.setUser(user);

        reviewService.insertReview(review);
        return ResponseEntity.status(HttpStatus.OK).body("게시글 작성 완료!");
    }

    //리뷰 상세 조회
    @GetMapping("/review/{uid}")
    public ResponseEntity<?> findReview(@PathVariable int uid){
        try {
            Review review = reviewService.findReview(uid);
            ResponseReviewDto responseReviewDto = new ResponseReviewDto(
                    review.getUid(),
                    review.getTitle(),
                    review.getContent(),
                    review.getUser().getName(),
                    review.getWriteDate(),
                    review.getVideoUrl()
            );
            return ResponseEntity.status(HttpStatus.OK).body(responseReviewDto);
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }
    }
}

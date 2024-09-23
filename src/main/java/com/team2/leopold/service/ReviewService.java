package com.team2.leopold.service;


import com.team2.leopold.entity.Review;
import com.team2.leopold.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    // 리뷰 작성
    @Transactional
    public void insertReview(Review review){
        reviewRepository.save(review);
    }

    // 리뷰 상세 조회
    public Review findReview(int uid){
        Optional<Review> foundReview = reviewRepository.findById(uid);
        if(foundReview.isPresent()){
            return foundReview.get();
        }
        return null;
    }


}

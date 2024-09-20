package com.team2.leopold.service;


import com.team2.leopold.entity.Review;
import com.team2.leopold.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void insertReview(Review review){
        reviewRepository.save(review);
    }
}

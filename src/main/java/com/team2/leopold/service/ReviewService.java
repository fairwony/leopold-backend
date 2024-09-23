package com.team2.leopold.service;


import com.team2.leopold.dto.ResponseAllReviewDto;
import com.team2.leopold.entity.Review;
import com.team2.leopold.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    public Review findReview(Integer uid){
        Optional<Review> foundReview = reviewRepository.findById(uid);
        if(foundReview.isPresent()){
            return foundReview.get();
        }
        return null;
    }

    // 리뷰 전체 조회
    public List<ResponseAllReviewDto> findAllReview(Integer page, Integer pageSize){
        Sort sort = Sort.by(Sort.Direction.DESC, "uid");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Review> reviews = reviewRepository.findAll(pageable);

        List<ResponseAllReviewDto> responseAllReviewDtoList = new ArrayList<>();
        for (Review review : reviews.getContent()){
            ResponseAllReviewDto responseAllReviewDto = new ResponseAllReviewDto(
                    review.getUid(),
                    review.getTitle(),
                    review.getUser().getName(),
                    review.getWriteDate());
            responseAllReviewDtoList.add(responseAllReviewDto);
        }
        return responseAllReviewDtoList;
    }

}

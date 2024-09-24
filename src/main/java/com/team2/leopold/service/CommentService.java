package com.team2.leopold.service;

import com.team2.leopold.entity.Comment;
import com.team2.leopold.entity.Review;
import com.team2.leopold.entity.User;
import com.team2.leopold.repository.CommentRepository;
import com.team2.leopold.repository.ReviewRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {
    private CommentRepository commentRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ReviewRepository reviewRepository) {
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
    }

    // 댓글 작성
    @Transactional
    public void insertComment(Comment comment, Integer reviewUid) throws BadRequestException {
        Optional<Review> optionalReview = reviewRepository.findById(reviewUid);
        if(optionalReview.isEmpty()) throw new BadRequestException();

        Review foundReview = optionalReview.get();
        comment.setReview(foundReview);
        commentRepository.save(comment);
    }

    //댓글 조회
    public Comment findComment(Integer uid){
        Optional<Comment> foundComment = commentRepository.findById(uid);
        if(foundComment.isPresent()){
            return foundComment.get();
        }
        return null;
    }

}
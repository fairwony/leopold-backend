package com.team2.leopold.controller;

import com.team2.leopold.dto.RequestCommentDto;
import com.team2.leopold.entity.Comment;
import com.team2.leopold.entity.Review;
import com.team2.leopold.entity.User;
import com.team2.leopold.repository.CommentRepository;
import com.team2.leopold.repository.ReviewRepository;
import com.team2.leopold.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CommentController {
    private CommentRepository commentRepository;
    private ReviewRepository reviewRepository;
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentRepository commentRepository, CommentService commentService){
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    // 댓글 작성
    @PostMapping("/comment/write/{uid}")
    public ResponseEntity<?> writeComment(@RequestBody RequestCommentDto requestCommentDto,
                                          HttpServletRequest request,
                                          @PathVariable(name = "uid") Integer ReviewUid){
        if (requestCommentDto.getContent() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터가 누락되었습니다.");

        HttpSession session = request.getSession(false);
        if(session == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되어 있지 않습니다.");


        User user = (User) session.getAttribute("user");

        Comment comment = new Comment();
        comment.setContent(requestCommentDto.getContent());
        comment.setUser(user);

        try {
            commentService.insertComment(comment, ReviewUid);
            return ResponseEntity.status(HttpStatus.OK).body("댓글 작성 완료!");
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 리뷰 게시글을 찾을 수 없습니다.");
        }
    }
}

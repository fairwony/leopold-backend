package com.team2.leopold.controller;

import com.team2.leopold.dto.ResponseReadNoticeDto;
import com.team2.leopold.entity.Notice;
import com.team2.leopold.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class NoticeController {
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
// 공지사항 상세 조회
    @GetMapping("/notices/{uid}")
    public ResponseEntity<?> readNotice(@PathVariable Integer uid) {

        try {
            Notice notice = noticeService.readNotice(uid);
            ResponseReadNoticeDto responseReadNoticeDto = new ResponseReadNoticeDto(
                    notice.getUid(),
                    notice.getTitle(),
                    notice.getContent(),
                    notice.getWriteDate(),
                    notice.getImageUrl(),
                    notice.getUser().getName()
            );
            return ResponseEntity.status(HttpStatus.OK).body(responseReadNoticeDto);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }
    }
// 공지사항 전체 조회
    @GetMapping("/notices")
    public ResponseEntity<?> getNotices(@RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Notice> notices = noticeService.getNotices(pageable);

        if (notices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }
            return ResponseEntity.status(HttpStatus.OK).body(notices);
    }
}

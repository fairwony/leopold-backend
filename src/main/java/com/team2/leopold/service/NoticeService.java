package com.team2.leopold.service;

import com.team2.leopold.dto.ResponseNoticeDto;
import com.team2.leopold.entity.Notice;
import com.team2.leopold.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
// 공지사항 전체 조회
//    public List<Notice> getNotices(Pageable pageable) {
//        return noticeRepository.findAll(pageable).stream().map(Notice::new).collect(Collectors.toList());
//    }
// 공지사항 상세 조회
    public Notice readNotice(int uid) {
        Optional<Notice> foundNotice = noticeRepository.findById(uid);
        if (foundNotice.isPresent()) {
            return foundNotice.get();
        }
        return null;
    }


}


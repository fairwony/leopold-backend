package com.team2.leopold.service;

import com.team2.leopold.entity.Notice;
import com.team2.leopold.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NoticeService {
    NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {

        this.noticeRepository = noticeRepository;
    }

    public List<Notice> getNotices() {
        return noticeRepository.findAll();
    }

    public Notice readNotice(int uid) {
        Optional<Notice> foundNotice = noticeRepository.findById(uid);
        if (foundNotice.isPresent()) {
            return foundNotice.get();
        }
        return null;
    }


}


package com.team2.leopold.service;

import com.team2.leopold.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NoticeService {
    private NoticeRepository repository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.repository = noticeRepository;
    }
}

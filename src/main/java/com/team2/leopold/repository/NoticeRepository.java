package com.team2.leopold.repository;

import com.team2.leopold.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
   // @Query("SELECT n FROM Notice n")
    Page<Notice> findAll(Pageable pageable);
}

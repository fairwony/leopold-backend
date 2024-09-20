package com.team2.leopold.repository;

import com.team2.leopold.entity.Download;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DownloadRepository extends JpaRepository<Download, Integer> {
//    @Query("Select d From Download d")
// Page<Download> findDownloads(Pageable pageable);
}

package com.team2.leopold.controller;

import com.team2.leopold.dto.ResponseDownloadDto;
import com.team2.leopold.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DownloadController {
    private final DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }
    // 자료실 전체 조회
    @GetMapping("/downloads")
    public ResponseEntity<?> readDownload(@RequestParam(name = "page") Integer page,
                                          @RequestParam(name = "size") Integer size) {
        List<ResponseDownloadDto> foundDownloads = downloadService.getDownloads(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(foundDownloads);
    }
    // 자료실 상세 조회
}

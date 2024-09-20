package com.team2.leopold.service;

import com.team2.leopold.dto.ResponseDownloadDto;
import com.team2.leopold.dto.ResponseNoticeDto;
import com.team2.leopold.entity.Download;
import com.team2.leopold.entity.Notice;
import com.team2.leopold.repository.DownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DownloadService {
    private final DownloadRepository downloadRepository;

    @Autowired
    public DownloadService(DownloadRepository downloadRepository) {
        this.downloadRepository = downloadRepository;
    }

    // 자료실 전체 조회
    public List<ResponseDownloadDto> getDownloads(Integer page, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "uid");
        Pageable pageable = PageRequest.of(page-1, pageSize, sort);
        Page<Download> downloads = downloadRepository.findAll(pageable);

        List<ResponseDownloadDto> responseDownloadDtoList = new ArrayList<>();
        for (Download download : downloads.getContent()) {
            ResponseDownloadDto responseDownloadDto = new ResponseDownloadDto(download.getUid(), download.getDownloadCategory().getName(), download.getTitle(), download.getUser().getName(), download.getWriteDate());
            responseDownloadDtoList.add(responseDownloadDto);
        }
        return responseDownloadDtoList;
    }

}

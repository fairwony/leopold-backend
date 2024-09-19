package com.team2.leopold.dto;

import com.team2.leopold.entity.User;

import java.time.LocalDate;

public class ResponseNoticeDto {
    private Integer uid;
    private String title;
    private String content;
    private LocalDate writeDate;
    private Integer hit;
    private String imageUrl;
    private String name;

    public ResponseNoticeDto(Integer uid, String title, String content, LocalDate writeDate, Integer hit, String imageUrl, String name) {
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.hit = hit;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(LocalDate writeDate) {
        this.writeDate = writeDate;
    }

    public Integer getHit() {
        return hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

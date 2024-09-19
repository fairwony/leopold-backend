package com.team2.leopold.dto;

import java.time.LocalDate;

public class ResponseNoticeDto {
    private Integer uid;
    private String title;
    private String name;
    private LocalDate writeDate;
    private Integer hit;

    public ResponseNoticeDto(Integer uid, String title, String name, LocalDate writeDate, Integer hit) {
        this.uid = uid;
        this.title = title;
        this.name = name;
        this.writeDate = writeDate;
        this.hit = hit;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

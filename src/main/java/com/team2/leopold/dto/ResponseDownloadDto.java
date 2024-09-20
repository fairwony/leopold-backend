package com.team2.leopold.dto;

import java.time.LocalDate;

public class ResponseDownloadDto {
    private Integer uid;
    private String categoryName;
    private String title;
    private String userName;
    private LocalDate writeDate;

    public ResponseDownloadDto(Integer uid, String categoryName, String title, String userName, LocalDate writeDate) {
        this.uid = uid;
        this.categoryName = categoryName;
        this.title = title;
        this.userName = userName;
        this.writeDate = writeDate;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(LocalDate writeDate) {
        this.writeDate = writeDate;
    }
}

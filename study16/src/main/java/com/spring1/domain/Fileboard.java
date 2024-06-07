package com.spring1.domain;

import java.sql.Timestamp;

public class Fileboard {
    private int no;
    private String title;
    private String content;
    private String filename;
    private Timestamp resdate;

    // Getters and Setters
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Timestamp getResdate() {
        return resdate;
    }

    public void setResdate(Timestamp resdate) {
        this.resdate = resdate;
    }
}

package com.example.myapplication;

public class Album {

    private String title;
    private String coverImage;

    public Album(){};

    public Album(String title, String coverImage) {
        this.title = title;
        this.coverImage = coverImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}

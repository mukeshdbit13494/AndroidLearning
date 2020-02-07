package com.example.phoneauth;

public class CommentList {
    private String id;
    private String description;

    public CommentList(){

    }

    public CommentList(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}

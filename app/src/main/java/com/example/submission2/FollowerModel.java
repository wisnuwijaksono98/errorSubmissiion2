package com.example.submission2;

public class FollowerModel {

    private String avatar;
    private String username;
    private String type;

    //constructor
    public FollowerModel(String avatar, String username, String type) {
        this.avatar = avatar;
        this.username = username;
        this.type = type;
    }

    //setter & getter
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

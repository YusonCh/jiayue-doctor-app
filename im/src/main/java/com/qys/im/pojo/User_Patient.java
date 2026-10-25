package com.qys.im.pojo;

public class User_Patient {

    private String user_name;
    private String password;
    private String name;
    private String image;
    private String connect;

    public User_Patient(String user_name, String password, String name, String image, String connect) {
        this.user_name = user_name;
        this.password = password;
        this.name = name;
        this.image = image;
        this.connect = connect;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }
}

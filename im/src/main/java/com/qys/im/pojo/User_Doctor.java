package com.qys.im.pojo;

public class User_Doctor {
    private String user_name;
    private String password;
    private String name;
    private String image;
    private String connect;
    private String department;
    private String level;
    private String genius;
    private String hospital;
    private String resume;
    private String time;
    private String rate;

    public User_Doctor(String user_name, String password, String name, String image, String connect, String department, String level, String genius, String hospital, String resume, String time, String rate) {
        this.user_name = user_name;
        this.password = password;
        this.name = name;
        this.image = image;
        this.connect = connect;
        this.department = department;
        this.level = level;
        this.genius = genius;
        this.hospital = hospital;
        this.resume = resume;
        this.time = time;
        this.rate = rate;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGenius() {
        return genius;
    }

    public void setGenius(String genius) {
        this.genius = genius;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}

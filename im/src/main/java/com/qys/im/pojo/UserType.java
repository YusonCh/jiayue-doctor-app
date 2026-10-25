package com.qys.im.pojo;

public class UserType {

    private int id;
    private String user_type;
    private String user_name;

    public UserType(int id, String user_type, String user_name) {
        this.id = id;
        this.user_type = user_type;
        this.user_name = user_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}

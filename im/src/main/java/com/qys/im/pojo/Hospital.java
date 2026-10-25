package com.qys.im.pojo;

public class Hospital {

    private int id;
    private String hospital_name;

    public Hospital(int id, String hospital_name) {
        this.id = id;
        this.hospital_name = hospital_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }
}

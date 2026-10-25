package com.qys.im.pojo;

import java.util.Date;

public class Illness_Record {
    private int id;
    private String user_name;
    private String name;
    private String doctor;
    private Date time;
    private String illness;
    private String reason;
    private String medicine;
    private String diet;
    private String cure;
    private String end;

    public Illness_Record(int id, String user_name, String name, String doctor, Date time, String illness, String reason, String medicine, String diet, String cure, String end) {
        this.id = id;
        this.user_name = user_name;
        this.name = name;
        this.doctor = doctor;
        this.time = time;
        this.illness = illness;
        this.reason = reason;
        this.medicine = medicine;
        this.diet = diet;
        this.cure = cure;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getCure() {
        return cure;
    }

    public void setCure(String cure) {
        this.cure = cure;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

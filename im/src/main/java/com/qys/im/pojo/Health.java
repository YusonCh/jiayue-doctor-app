package com.qys.im.pojo;

import java.sql.Time;
import java.util.Date;

public class Health {
    private int id;
    private String user_name;
    private String patient_name;
    private double height;
    private double weight;
    private String kpa;
    private String heartbeat;
    private String sports;
    private String sleep;
    private Date time;
  ;
    public Health(int id, String user_name, String patient_name ,double height, double weight, String kpa, String heartbeat, String sports, String sleep, Date time) {
        this.id = id;
        this.user_name = user_name;
        this.height = height;
        this.weight = weight;
        this.kpa = kpa;
        this.heartbeat = heartbeat;
        this.sports = sports;
        this.sleep = sleep;
        this.patient_name=patient_name;
        this.time=time;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getKpa() {
        return kpa;
    }

    public void setKpa(String kpa) {
        this.kpa = kpa;
    }

    public String getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(String heartbeat) {
        this.heartbeat = heartbeat;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

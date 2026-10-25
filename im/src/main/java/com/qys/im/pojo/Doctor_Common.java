package com.qys.im.pojo;

public class Doctor_Common {

    private int id;
    private String user_patient;
    private String user_doctor;

    public Doctor_Common(int id, String user_patient, String user_doctor) {
        this.id = id;
        this.user_patient = user_patient;
        this.user_doctor = user_doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_patient() {
        return user_patient;
    }

    public void setUser_patient(String user_patient) {
        this.user_patient = user_patient;
    }

    public String getUser_doctor() {
        return user_doctor;
    }

    public void setUser_doctor(String user_doctor) {
        this.user_doctor = user_doctor;
    }
}

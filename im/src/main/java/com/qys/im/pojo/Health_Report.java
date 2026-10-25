package com.qys.im.pojo;

public class Health_Report {
    private int id;
    private String user_name;
    private String patient_name;
    private String reason;
    private String name;
    private String sex;
    private double height;
    private double weight;
    private String cure;
    private String diet;
    private String medicine;

    public Health_Report(int id, String user_name, String patient_name, String reason, String name, String sex, double height, double weight, String cure, String diet, String medicine) {
        this.id = id;
        this.user_name = user_name;
        this.patient_name = patient_name;
        this.reason = reason;
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.cure = cure;
        this.diet = diet;
        this.medicine = medicine;
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

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCure() {
        return cure;
    }

    public void setCure(String cure) {
        this.cure = cure;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }
}

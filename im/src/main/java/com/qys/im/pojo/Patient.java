package com.qys.im.pojo;

public class Patient {

    private int id;
    private String user_name;
    private String name;
    private String sex;
    private double height;
    private double weight;
    private String allergy;
    private String illness;
    private String diet;
    private String medicine;
    private String age;
    private String relationship;

    public Patient(int id, String user_name, String name, String sex, double height, double weight, String allergy, String illness, String diet, String medicine, String age, String relationship) {
        this.id = id;
        this.user_name = user_name;
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.allergy = allergy;
        this.illness = illness;
        this.diet = diet;
        this.medicine = medicine;
        this.age = age;
        this.relationship = relationship;
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

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}

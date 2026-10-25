package com.qys.im.pojo;

public class Topsis {
    private String user_name;
    private String name;
    private int answer_quality;
    private int answer_speed;
    private int answer_attitude;
    private int answer_quantity;
    private int answer_influence;
    private int answer_satisfaction;
    private int comprehensive_evaluation;

    public Topsis(String user_name, String name, int answer_quality, int answer_speed, int answer_attitude, int answer_quantity, int answer_influence, int answer_satisfaction, int comprehensive_evaluation) {
        this.user_name = user_name;
        this.name = name;
        this.answer_quality = answer_quality;
        this.answer_speed = answer_speed;
        this.answer_attitude = answer_attitude;
        this.answer_quantity = answer_quantity;
        this.answer_influence = answer_influence;
        this.answer_satisfaction = answer_satisfaction;
        this.comprehensive_evaluation = comprehensive_evaluation;
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

    public int getAnswer_quality() {
        return answer_quality;
    }

    public void setAnswer_quality(int answer_quality) {
        this.answer_quality = answer_quality;
    }

    public int getAnswer_speed() {
        return answer_speed;
    }

    public void setAnswer_speed(int answer_speed) {
        this.answer_speed = answer_speed;
    }

    public int getAnswer_attitude() {
        return answer_attitude;
    }

    public void setAnswer_attitude(int answer_attitude) {
        this.answer_attitude = answer_attitude;
    }

    public int getAnswer_quantity() {
        return answer_quantity;
    }

    public void setAnswer_quantity(int answer_quantity) {
        this.answer_quantity = answer_quantity;
    }

    public int getAnswer_influence() {
        return answer_influence;
    }

    public void setAnswer_influence(int answer_influence) {
        this.answer_influence = answer_influence;
    }

    public int getAnswer_satisfaction() {
        return answer_satisfaction;
    }

    public void setAnswer_satisfaction(int answer_satisfaction) {
        this.answer_satisfaction = answer_satisfaction;
    }

    public int getComprehensive_evaluation() {
        return comprehensive_evaluation;
    }

    public void setComprehensive_evaluation(int comprehensive_evaluation) {
        this.comprehensive_evaluation = comprehensive_evaluation;
    }
}

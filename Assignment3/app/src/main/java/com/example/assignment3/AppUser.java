package com.example.assignment3;

import java.util.Date;


public class AppUser {
    private Integer userid;
    private String name;
    private String surename;
    private String email;
    private Date dob;
    private String gender;
    private String address;
    private Double height;
    private Double weight;
    private Integer postcode;
    private Integer activitylevel;
    private Integer stepspermile;

    public AppUser(Integer id)
    {
        this.userid=id;
    }

    public AppUser(Integer userid, String name, String surename, String email, Date dob, String gender,
                   Double height, Double weight, String address,
                   Integer postcode, Integer activitylevel, Integer stepspermile) {
        this.userid=userid;
        this.name = name;
        this.surename = surename;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.height = height;
        this.postcode = postcode;
        this.weight = weight;
        this.activitylevel= activitylevel;
        this.stepspermile=stepspermile;
        this.gender= gender;
    }
public AppUser(){}


    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

    public String getUserFirstName() {
        return name;
    }

    public void setUserFirstName(String firstName) {
        this.name = name;
    }
    public String getUserSureName() {
        return surename;
    }

    public void setUserSureName(String surname) {
        this.surename = surename;
    }

    public String getUserAddress() {
        return address;
    }

    public void setUserAddress(String address) {
        this.address = address;
    }
    public Date getUserDoB() {
        return dob;
    }

    public void setUserDoB(Date dob) {
        this.dob = dob;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public Double getUserHeight() {
        return height;
    }

    public void setUserHeight(Double height) {
        this.height = height;
    }

    public Double getUserWeight() {
        return weight;
    }

    public void setUserWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getUserActivityLevel() {
        return activitylevel;
    }

    public void setUserActivityLevel(Integer activitylevel) {
        this.activitylevel = activitylevel;
    }

    public Integer getUserStepPerMile() {
        return stepspermile;
    }

    public void setUserStepPerMile(Integer stepspermile) {
        this.stepspermile = stepspermile;
    }

    public Integer getUserPostCode() {
        return postcode;
    }

    public void setUserPostCode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getUserGender() {
        return gender;
    }

    public void setUserGender(String gender) {
        this.gender = gender;
    }
}

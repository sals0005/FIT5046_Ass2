package com.example.assignment3;


import java.util.Date;

public class credential {
    private AppUser userid;
    private String username;
    private String passwordhash;
    private Date signupdate;

    public credential(Integer userid,String username, String passwordhash, Date signupdate) {
        this.userid = new AppUser(userid);
        this.username = username;
        this.passwordhash = passwordhash;
        this.signupdate = signupdate;
    }


    public AppUser getUserId() {
        return userid;
    }

    public void setUserId(AppUser userId) {
        this.userid = userid;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordhash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordhash = passwordhash;
    }

    public Date getSignupDateDate() {
        return signupdate;
    }

    public void setSignupDateDate(Date SignupDate) {
        this.signupdate = SignupDate;
    }
}
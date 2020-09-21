package com.example.assignment3;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class User implements Parcelable {
    private Integer userId = 0;
    private String firstName = "";
    private String surname = "";
    private String address = "";
    private Date dob = null;
    private String email = "";
    private Double height = 0.0;
    private Integer postcode = 0;
    private Double weight = 0.0;
    private Integer activityLevel = 0;
    private Integer stepsPerMile = 0;
    private String gender = "";

    public User(Parcel in) {
        this.userId = in.readInt();
    }

    public User(Integer userId, String firstName, String surname, String address, Date dob, String email,
                Double height, Double weight, Integer postcode, Integer activityLevel, Integer stepsPerMile
            , String gender) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.postcode = postcode;
        this.activityLevel = activityLevel;
        this.stepsPerMile = stepsPerMile;
        this.gender = gender;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(userId);
        parcel.writeString(firstName);
        parcel.writeString(surname);
        parcel.writeString(address);
        parcel.writeString(gender);
        parcel.writeDouble(height);
        parcel.writeString(email);
        //parcel.write(dob);
        parcel.writeDouble(weight);
        parcel.writeInt(stepsPerMile);
        parcel.writeInt(activityLevel);
        parcel.writeInt(postcode);
    }

    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setStepsPerMile(Integer stepsPerMile) {
        this.stepsPerMile = stepsPerMile;
    }

    public Integer getStepsPerMile() {
        return stepsPerMile;
    }

    public void setActivityLevel(Integer activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Integer getActivityLevel() {
        return activityLevel;
    }
    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getHeight() {
        return height;
    }


}


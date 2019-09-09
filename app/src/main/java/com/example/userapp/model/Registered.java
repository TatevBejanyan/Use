package com.example.userapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registered {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("age")
    @Expose
    private int age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Registered withDate(String date) {
        this.date = date;
        return this;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Registered withAge(int age) {
        this.age = age;
        return this;
    }
}

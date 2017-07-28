package com.zolostaystask.models;

public class User {

    private String name;
    private String email;
    private String pwd;
    private String phone;

    public User(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public User(String name, String email, String pwd, String phone) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
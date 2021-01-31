package com.example.farmerassist07.Modal;

import com.example.farmerassist07.Modal.UserType;

public class User {
    private String uid, name, phone, address;
    private UserType userType;

    public User() {
    }

    public User(String uid, String name, String phone, String address, UserType userType) {
        this.uid = uid;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.userType = userType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}

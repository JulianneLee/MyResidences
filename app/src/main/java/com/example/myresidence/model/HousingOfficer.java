package com.example.myresidence.model;

public class HousingOfficer{
    private int officerID;
    private String staffID;
    private String username;
    private String password;
    private String fullName;

    public HousingOfficer() {
    }

    public HousingOfficer(int officerID, String staffID, String username, String password, String fullName) {
        this.officerID = officerID;
        this.staffID = staffID;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "HousingOfficer{" +
                "officerID=" + officerID +
                ", staffID='" + staffID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}


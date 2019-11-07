package com.example.myresidence.model;

public class Application {
    private int applicationID, residenceID;
    private String applicationDate;
    private String requiredMonth;
    private String requiredYear;
    private String status;

    public Application() {
    }

    public Application(int applicationID, String applicationDate, String requiredMonth,
                       String requiredYear, String status, int residenceID) {
        this.applicationID = applicationID;
        this.applicationDate = applicationDate;
        this.requiredMonth = requiredMonth;
        this.requiredYear = requiredYear;
        this.status = status;
        this.residenceID = residenceID;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getRequiredMonth() {
        return requiredMonth;
    }

    public void setRequiredMonth(String requiredMonth) {
        this.requiredMonth = requiredMonth;
    }

    public String getRequiredYear() {
        return requiredYear;
    }

    public void setRequiredYear(String requiredYear) {
        this.requiredYear = requiredYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResidenceID() {
        return residenceID;
    }

    public void setResidenceID(int residenceID) {
        this.residenceID = residenceID;
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationID=" + applicationID +
                ", applicationDate='" + applicationDate + '\'' +
                ", requiredMonth='" + requiredMonth + '\'' +
                ", requiredYear='" + requiredYear + '\'' +
                ", status='" + status + '\'' +
                ", residenceID='" + residenceID + '\'' +
                '}';
    }
}

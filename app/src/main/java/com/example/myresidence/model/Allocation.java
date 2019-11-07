package com.example.myresidence.model;

public class Allocation {
    private String fromDate;
    private String duration;
    private String endDate;

    public Allocation() {
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "fromDate='" + fromDate + '\'' +
                ", duration='" + duration + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}


package com.example.myresidence.model;

public class Residence {
    private int residenceID;
    private String address;
    private int numUnits;
    private int sizePerUnit;
    private double monthlyRental;

    public Residence() {
    }

    public Residence(int residenceID, String address, int numUnits, int sizePerUnit, double monthlyRental) {
        this.residenceID = residenceID;
        this.address = address;
        this.numUnits = numUnits;
        this.sizePerUnit = sizePerUnit;
        this.monthlyRental = monthlyRental;
    }

    public int getResidenceID() {
        return residenceID;
    }

    public void setResidenceID(int residenceID) {
        this.residenceID = residenceID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumUnits() {
        return numUnits;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    public int getSizePerUnit() {
        return sizePerUnit;
    }

    public void setSizePerUnit(int sizePerUnit) {
        this.sizePerUnit = sizePerUnit;
    }

    public double getMonthlyRental() {
        return monthlyRental;
    }

    public void setMonthlyRental(double monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    @Override
    public String toString() {
        return "Residence{" +
                "residenceID=" + residenceID +
                ", address='" + address + '\'' +
                ", numUnits=" + numUnits +
                ", sizePerUnit=" + sizePerUnit +
                ", monthlyRental=" + monthlyRental +
                '}';
    }
}


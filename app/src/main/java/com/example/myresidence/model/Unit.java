package com.example.myresidence.model;

public class Unit {
    private int unitNo, availability, residenceID;

    public Unit() {
    }

    public Unit(int unitNo, int availability, int residenceID) {
        this.unitNo = unitNo;
        this.availability = availability;
        this.residenceID = residenceID;
    }

    public int getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(int unitNo) {
        this.unitNo = unitNo;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getResidenceID() {
        return residenceID;
    }

    public void setResidenceID(int residenceID) {
        this.residenceID = residenceID;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitNo=" + unitNo +
                ", availability=" + availability +
                ", residenceID=" + residenceID +
                '}';
    }
}

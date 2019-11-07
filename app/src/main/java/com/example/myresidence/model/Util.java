package com.example.myresidence.model;

public class Util {
    //Database related item
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mhs_db";
    public static final String TABLE_NAME = "application";
    public static final String TABLE_RESIDENCE = "residence";
    public static final String TABLE_UNIT = "unit";
    public static final String TABLE_ALLOCATION = "allocation";
    public static final String TABLE_APPLICANT = "applicant";
    public static final String TABLE_OFFICER = "officer";

    //Application table columns
    public static final String KEY_ID="ID";
    public static final String KEY_DATE="Date";
    public static final String KEY_REQUIRED_MONTH="Req_Month";
    public static final String KEY_REQUIRED_YEAR="Req_Year";
    public static final String KEY_STATUS="Status";
    public static final String KEY_RID="Residence_ID";

    //Residence table columns
    public static final String KEY_RESIDENCE_ID="ID";
    public static final String KEY_ADDRESS="Address";
    public static final String KEY_NUM_UNITS="Num_Units";
    public static final String KEY_SIZE_PER_UNIT="Size_Per_Unit";
    public static final String KEY_MONTHLY_RENTAL="Monthly_Rental";
    public static final String KEY_APP_ID="Application_ID";
    public static final String KEY_OFFICER_INCHARGE="Officer_Incharge";

    //Unit table columns
    public static final String KEY_UNIT_NUM="Unit_Num";
    public static final String KEY_AVAILABILITY="Availability";
    public static final String KEY_RES_ID="Residence_ID";

    //Allocation table columns
    public static final String KEY_FROM_DATE="From_Date";
    public static final String KEY_TO_DATE="To_Date";
    public static final String KEY_DURATION="Duration";
    public static final String KEY_APPLICATION_ID="Application_ID";

    //Applicant table columns
    public static final String KEY_APPLICANT_ID="ID";
    public static final String KEY_USERNAME="Username";
    public static final String KEY_PASSWORD="Password";
    public static final String KEY_FULLNAME="Full_Name";
    public static final String KEY_EMAIL="Email";
    public static final String KEY_MONTHLY_INCOME="Monthly_Income";

    //Officer table columns
    public static final String KEY_OFFICER_ID="Officer_ID";
    public static final String KEY_OFFICER_USERNAME="Username";
    public static final String KEY_OFFICER_PASSWORD="Password";
    public static final String KEY_OFFICER_FULLNAME="Full_Name";
    public static final String KEY_STAFF_ID="Staff_ID";
}

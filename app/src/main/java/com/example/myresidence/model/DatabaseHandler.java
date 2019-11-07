package com.example.myresidence.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.UniversalTimeScale;
import android.telephony.AccessNetworkConstants;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myresidence.R;

import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    String SQL_Application, SQL_Residence, SQL_Unit, SQL_Allocation,
            SQL_Applicant, SQL_Officer;
    public DatabaseHandler(@Nullable Context context){
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //SQL for applicant
        String CREATE_APPLICANT_TABLE =
                "CREATE TABLE " + Util.TABLE_APPLICANT + "(" +
                        Util.KEY_APPLICANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Util.KEY_USERNAME + " TEXT, " +
                        Util.KEY_PASSWORD + " TEXT, " +
                        Util.KEY_FULLNAME + " TEXT, " +
                        Util.KEY_EMAIL + " TEXT, " +
                        Util.KEY_MONTHLY_INCOME + " NUMERIC);";

        //SQL for officer
        String CREATE_OFFICER_TABLE =
                "CREATE TABLE " + Util.TABLE_OFFICER + "(" +
                        Util.KEY_OFFICER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Util.KEY_OFFICER_USERNAME + " TEXT, " +
                        Util.KEY_OFFICER_PASSWORD + " TEXT, " +
                        Util.KEY_OFFICER_FULLNAME + " TEXT, " +
                        Util.KEY_STAFF_ID + " TEXT);";

        //SQL for residence
        String CREATE_RESIDENCE_TABLE =
                "CREATE TABLE " + Util.TABLE_RESIDENCE + "(" +
                        Util.KEY_RESIDENCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Util.KEY_ADDRESS + " TEXT, " +
                        Util.KEY_NUM_UNITS + " TEXT, " +
                        Util.KEY_SIZE_PER_UNIT + " INTEGER, " +
                        Util.KEY_MONTHLY_RENTAL + " NUMERIC ," +
                        Util.KEY_APP_ID + " INTEGER ," +
                        Util.KEY_OFFICER_INCHARGE + " INTEGER ," +
                        " FOREIGN KEY (" + Util.KEY_APP_ID + ") REFERENCES " + Util.TABLE_NAME + "(" + Util.KEY_ID + ")," +
                        " FOREIGN KEY (" + Util.KEY_OFFICER_INCHARGE +  ") REFERENCES " + Util.TABLE_OFFICER + "(" + Util.KEY_OFFICER_ID + "));";

        //SQL for application
        String CREATE_APPLICATION_TABLE =
                "CREATE TABLE " + Util.TABLE_NAME + "(" +
                        Util.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Util.KEY_DATE + " DATE, " +
                        Util.KEY_REQUIRED_MONTH + " TEXT, " +
                        Util.KEY_REQUIRED_YEAR + " TEXT, " +
                        Util.KEY_STATUS + " TEXT," +
                        Util.KEY_RID + " INTEGER," +
                        " FOREIGN KEY (" + Util.KEY_RID + ") REFERENCES " + Util.TABLE_RESIDENCE + "(" + Util.KEY_RESIDENCE_ID + "));";

        //SQL for unit
        String CREATE_UNIT_TABLE =
                "CREATE TABLE " + Util.TABLE_UNIT + "(" +
                        Util.KEY_UNIT_NUM + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Util.KEY_AVAILABILITY + " INTEGER," +
                        Util.KEY_RES_ID + " INTEGER," +
                        " FOREIGN KEY (" + Util.KEY_RES_ID + ") " + " REFERENCES " + Util.TABLE_RESIDENCE + "(" + Util.KEY_RESIDENCE_ID + "));";

        //SQL for allocation
        String CREATE_ALLOCATION_TABLE =
                "CREATE TABLE " + Util.TABLE_ALLOCATION + "(" +
                        Util.KEY_FROM_DATE + " DATE, " +
                        Util.KEY_DURATION + " INTEGER, " +
                        Util.KEY_TO_DATE + " DATE, " +
                        Util.KEY_APPLICATION_ID + " INTEGER," +
                        "FOREIGN KEY (" + Util.KEY_APPLICATION_ID + ") REFERENCES " + Util.TABLE_NAME + "(" + Util.KEY_ID + "));";


        db.execSQL(CREATE_APPLICANT_TABLE);
        db.execSQL(CREATE_OFFICER_TABLE);
        db.execSQL(CREATE_APPLICATION_TABLE);
        db.execSQL(CREATE_RESIDENCE_TABLE);
        db.execSQL(CREATE_UNIT_TABLE);
        db.execSQL(CREATE_ALLOCATION_TABLE);


        SQL_Application = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        SQL_Residence = "DROP TABLE IF EXISTS " + Util.TABLE_RESIDENCE;
        SQL_Unit = "DROP TABLE IF EXISTS " + Util.TABLE_UNIT;
        SQL_Allocation = "DROP TABLE IF EXISTS " + Util.TABLE_ALLOCATION;
        SQL_Applicant = "DROP TABLE IF EXISTS " + Util.TABLE_APPLICANT;
        SQL_Officer = "DROP TABLE IF EXISTS " + Util.TABLE_OFFICER;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_Application);
        onCreate(db);
        db.execSQL(SQL_Residence);
        onCreate(db);
        db.execSQL(SQL_Unit);
        onCreate(db);
        db.execSQL(SQL_Allocation);
        onCreate(db);
        db.execSQL(SQL_Applicant);
        onCreate(db);
        db.execSQL(SQL_Officer);
        onCreate(db);
    }

    public void addApplication(Application application){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_ID, application.getApplicationID());
        contentValues.put(Util.KEY_DATE, application.getApplicationDate());
        contentValues.put(Util.KEY_REQUIRED_MONTH, application.getRequiredMonth());
        contentValues.put(Util.KEY_REQUIRED_YEAR, application.getRequiredYear());
        contentValues.put(Util.KEY_STATUS, application.getStatus());
        contentValues.put(Util.KEY_RID, application.getResidenceID());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    public void addApplicant(Applicant applicant){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_USERNAME, applicant.getUsername());
        contentValues.put(Util.KEY_PASSWORD, applicant.getPassword());
        contentValues.put(Util.KEY_FULLNAME, applicant.getFullName());
        contentValues.put(Util.KEY_EMAIL, applicant.getEmail());
        contentValues.put(Util.KEY_MONTHLY_INCOME, applicant.getMonthlyIncome());

        db.insert(Util.TABLE_APPLICANT, null, contentValues);
        db.close();
    }

    public void addOfficer(HousingOfficer housingOfficer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_OFFICER_USERNAME, housingOfficer.getUsername());
        contentValues.put(Util.KEY_OFFICER_PASSWORD, housingOfficer.getPassword());
        contentValues.put(Util.KEY_OFFICER_FULLNAME, housingOfficer.getFullName());
        contentValues.put(Util.KEY_STAFF_ID, housingOfficer.getStaffID());

        db.insert(Util.TABLE_OFFICER, null, contentValues);
        db.close();
    }

    public Application getApplication(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID,
                        Util.KEY_DATE,
                        Util.KEY_REQUIRED_MONTH,
                        Util.KEY_REQUIRED_YEAR,
                        Util.KEY_STATUS},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);
        Application application = new Application();

        if(cursor != null && cursor.moveToFirst()){
            application.setApplicationID(Integer.parseInt(cursor.getString(0)));
            application.setApplicationDate(cursor.getString(1));
            application.setRequiredMonth(cursor.getString(2));
            application.setRequiredYear(cursor.getString(3));
            application.setStatus(cursor.getString(4));
            application.setResidenceID(Integer.parseInt(cursor.getString(5)));
        }

        return application;
    }

    public Applicant getApplicant(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_APPLICANT,
                new String[]{Util.KEY_APPLICANT_ID,
                        Util.KEY_USERNAME,
                        Util.KEY_PASSWORD,
                        Util.KEY_FULLNAME,
                        Util.KEY_EMAIL,
                        Util.KEY_MONTHLY_INCOME},
                Util.KEY_USERNAME + "=?", new String[]{username},
                null, null, null);
        Applicant applicant = new Applicant();

        if(cursor != null && cursor.moveToFirst()){
            applicant.setUsername(cursor.getString(1));
            applicant.setPassword(cursor.getString(2));
            applicant.setFullName(cursor.getString(3));
            applicant.setEmail(cursor.getString(4));
            applicant.setMonthlyIncome(Double.parseDouble(cursor.getString(5)));
        }

        return applicant;
    }

    public HousingOfficer getOfficer(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_OFFICER,
                new String[]{Util.KEY_OFFICER_ID,
                        Util.KEY_OFFICER_USERNAME,
                        Util.KEY_OFFICER_PASSWORD,
                        Util.KEY_OFFICER_FULLNAME,
                        Util.KEY_STAFF_ID},
                Util.KEY_OFFICER_USERNAME+ "=?", new String[]{username},
                null, null, null);
        HousingOfficer officer = new HousingOfficer();

        if(cursor != null && cursor.moveToFirst()){
            officer.setUsername(cursor.getString(1));
            officer.setPassword(cursor.getString(2));
            officer.setFullName(cursor.getString(3));
            officer.setStaffID(cursor.getString(4));
        }

        return officer;
    }

    public Residence getResidence(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_RESIDENCE,
                new String[]{Util.KEY_RESIDENCE_ID,
                        Util.KEY_ADDRESS,
                        Util.KEY_NUM_UNITS,
                        Util.KEY_SIZE_PER_UNIT,
                        Util.KEY_MONTHLY_RENTAL,
                        Util.KEY_APPLICATION_ID,
                        Util.KEY_OFFICER_INCHARGE},
                Util.KEY_OFFICER_INCHARGE + "=?", new String[]{username},
                null, null, null);
        Residence residence = new Residence();

        if(cursor != null && cursor.moveToFirst()){
            residence.setAddress(cursor.getString(1));
            residence.setNumUnits(Integer.parseInt(cursor.getString(1)));
            residence.setSizePerUnit(Integer.parseInt(cursor.getString(1)));
            residence.setMonthlyRental(Double.parseDouble(cursor.getString(1)));
        }

        return residence;
    }

    public List<Application> getAllApplications(){
        List<Application> applicationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll, null);
        if(cursor.moveToFirst() == true){
            do{
                Application application = new Application();
                application.setApplicationID(Integer.parseInt(cursor.getString(0)));
                application.setApplicationDate(cursor.getString(1));
                application.setRequiredMonth(cursor.getString(2));
                application.setRequiredYear(cursor.getString(3));
                application.setStatus(cursor.getString(4));
                applicationList.add(application);
            } while (cursor.moveToNext());
        }
        db.close();
        return applicationList;
    }

    public int updateStatus(Application application){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_STATUS, application.getStatus());

        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String[]{String.valueOf(application.getApplicationID())});
    }

    public int UpdateApplication(Application application){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_DATE, application.getApplicationDate());
        contentValues.put(Util.KEY_REQUIRED_MONTH, application.getRequiredMonth());
        contentValues.put(Util.KEY_REQUIRED_YEAR, application.getRequiredYear());
        contentValues.put(Util.KEY_STATUS, application.getStatus());

        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String[]{String.valueOf(application.getApplicationID())});
    }

    public int UpdateResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_ADDRESS, residence.getAddress());
        contentValues.put(Util.KEY_NUM_UNITS, residence.getNumUnits());
        contentValues.put(Util.KEY_NUM_UNITS, residence.getNumUnits());
        contentValues.put(Util.KEY_SIZE_PER_UNIT, residence.getSizePerUnit());
        contentValues.put(Util.KEY_MONTHLY_RENTAL, residence.getMonthlyRental());

        return db.update(Util.TABLE_RESIDENCE, contentValues, Util.KEY_RESIDENCE_ID + "=?",
                new String[]{String.valueOf(residence.getResidenceID())});
    }

    public void DeleteResidence(Residence residence){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_RESIDENCE, Util.KEY_RESIDENCE_ID + "=?",
                new String[]{String.valueOf(residence.getResidenceID())});
        db.close();
    }
}


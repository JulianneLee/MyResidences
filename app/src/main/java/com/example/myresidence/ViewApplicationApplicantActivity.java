package com.example.myresidence;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myresidence.model.ApplicantAppItemAdapter;
import com.example.myresidence.model.Application;
import com.example.myresidence.model.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class ViewApplicationApplicantActivity extends AppCompatActivity {
    ApplicantAppItemAdapter applicantAppItemAdapter;
    RecyclerView recyclerView;
    DatabaseHandler databaseHandler;
    Application application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_app_list);


        List<Application> applications;
        applications = getData();
//        databaseHandler = new DatabaseHandler(ViewApplicationActivity.this);
//        recyclerView = findViewById(R.id.viewApplicationRecyclerView);
//
//        getAllApplications();

        recyclerView = findViewById(R.id.viewApplicationApplicantRecyclerView);
        applicantAppItemAdapter = new ApplicantAppItemAdapter(getApplication(), applications);
        recyclerView.setAdapter(applicantAppItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

    }

    public void getAllApplications(){
        application = null;
        List<Application> applicationList = databaseHandler.getAllApplications();
        applicantAppItemAdapter = new ApplicantAppItemAdapter(this, applicationList);
        RecyclerView recyclerView = findViewById(R.id.viewApplicationApplicantRecyclerView);
        recyclerView.setAdapter(applicantAppItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //Sample data
    private List<Application> getData(){
        List<Application> applications = new ArrayList<>();
        applications.add(new Application(1,"27/10/2019", "May", "2020",
                "New", 1));

        applications.add(new Application(2,"29/10/2019", "August", "2020",
                "Waitlist", 2));

        return applications;
    }
}


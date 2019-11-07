package com.example.myresidence;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myresidence.model.Application;
import com.example.myresidence.model.ApplicationItemAdapter;
import com.example.myresidence.model.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class ViewApplicationActivity extends AppCompatActivity{

    ApplicationItemAdapter applicationItemAdapter;
    RecyclerView recyclerView;
    DatabaseHandler databaseHandler;
    Application application;
    Button approve, waitlist, reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_app);

        List<Application> applications;
        applications = getData();
//        databaseHandler = new DatabaseHandler(ViewApplicationActivity.this);
//        recyclerView = findViewById(R.id.viewApplicationRecyclerView);
//
//        getAllApplications();

        recyclerView = findViewById(R.id.viewApplicationRecyclerView);
        applicationItemAdapter = new ApplicationItemAdapter(getApplication(), applications);
        recyclerView.setAdapter(applicationItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

//        applicationItemAdapter.setOnItemClickListener(new ApplicationItemAdapter.setOnItemClickListener() {
//            @Override
//            public void approveClick(int position) {
//                databaseHandler.updateStatus();
//            }
//
//        });

    }

    public void getAllApplications(){
        application = null;
        List<Application> applicationList = databaseHandler.getAllApplications();
        applicationItemAdapter = new ApplicationItemAdapter(this, applicationList);
        RecyclerView recyclerView = findViewById(R.id.viewApplicationRecyclerView);
        recyclerView.setAdapter(applicationItemAdapter);
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


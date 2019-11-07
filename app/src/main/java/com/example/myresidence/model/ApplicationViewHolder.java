package com.example.myresidence.model;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myresidence.R;

public class ApplicationViewHolder extends RecyclerView.ViewHolder {
    TextView applicationID, residenceID, requiredMonth, requiredYear, status;
    EditText appIDEditText, residenceIDEditText, reqMonth, reqYear, statusEditText;
    Button approve, reject, waitlist;

    ApplicationViewHolder(View convertView){
        super(convertView);

        //Text View
        applicationID = convertView.findViewById(R.id.appID);
        residenceID = convertView.findViewById(R.id.residenceID);
        requiredMonth = convertView.findViewById(R.id.reqMonth);
        requiredYear = convertView.findViewById(R.id.reqYear);
        status = convertView.findViewById(R.id.status);

        //Edit Text
        appIDEditText = convertView.findViewById(R.id.appIDEditText);
        residenceIDEditText = convertView.findViewById(R.id.rIDEditText);
        reqMonth = convertView.findViewById(R.id.reqMonthEditText);
        reqYear = convertView.findViewById(R.id.reqYearEditText);
        statusEditText = convertView.findViewById(R.id.statusEditText);

        //Button
        approve = convertView.findViewById(R.id.approveBtn);
        reject = convertView.findViewById(R.id.rejectBtn);
        waitlist = convertView.findViewById(R.id.waitlistBtn);
    }
}

package com.example.myresidence.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myresidence.R;

import java.util.List;

public class ApplicantAppItemAdapter extends RecyclerView.Adapter<ApplicantAppViewHolder> {
    List<Application> mApplicationItem;
    LayoutInflater mInflater;
    Context context;

    public ApplicantAppItemAdapter(@NonNull Context context, @NonNull List<Application> objects) {
        mApplicationItem = objects;
        this.context = context;
    }

    @Override
    public ApplicantAppViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        mInflater = LayoutInflater.from(context);

        View view = mInflater.inflate(R.layout.view_app_applicant, parent, false);

        ApplicantAppViewHolder applicantAppViewHolder = new ApplicantAppViewHolder(view);
        return applicantAppViewHolder;
    }

    @Override
    public void onBindViewHolder(final ApplicantAppViewHolder applicantAppViewHolder, final int position){
        applicantAppViewHolder.appIDEditText.setText(Integer.toString(mApplicationItem.get(position).getApplicationID()));
        applicantAppViewHolder.reqMonth.setText(mApplicationItem.get(position).getRequiredMonth());
        applicantAppViewHolder.reqYear.setText(mApplicationItem.get(position).getRequiredYear());
        applicantAppViewHolder.statusEditText.setText(mApplicationItem.get(position).getStatus());
        applicantAppViewHolder.residenceIDEditText.setText(Integer.toString(mApplicationItem.get(position).getResidenceID()));
    }

    @Override
    public int getItemCount(){
        return mApplicationItem.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}

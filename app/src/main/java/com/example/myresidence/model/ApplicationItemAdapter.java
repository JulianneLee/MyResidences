package com.example.myresidence.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myresidence.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationItemAdapter extends RecyclerView.Adapter<ApplicationViewHolder> {
    List<Application> mApplicationItem;
    LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener;
    Context context;
    Button approve, reject, waitlist;

    //
    public interface OnItemClickListener{
        void onApproveClicked(int position);
        void onRejectClicked(int position);
        void onWaitlistClicked(int position);
    }

    //
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public ApplicationItemAdapter(@NonNull Context context, @NonNull List<Application> objects) {
        mApplicationItem = objects;
        this.context = context;
    }

    @Override
    public ApplicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        mInflater = LayoutInflater.from(context);

        View view = mInflater.inflate(R.layout.view_application, parent, false);
        approve = view.findViewById(R.id.approveBtn);
        reject = view.findViewById(R.id.rejectBtn);
        waitlist = view.findViewById(R.id.waitlistBtn);

        //
//        approve.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                if(onItemClickListener != null){
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION){
//                        onItemClickListener.onApproveClicked(position);
//                    }
//                }
//            }
//        });

        ApplicationViewHolder applicationViewHolder = new ApplicationViewHolder(view);


        return applicationViewHolder;
    }

    @Override
    public void onBindViewHolder(final ApplicationViewHolder applicationViewHolder, final int position){
        applicationViewHolder.appIDEditText.setText(Integer.toString(mApplicationItem.get(position).getApplicationID()));
        applicationViewHolder.reqMonth.setText(mApplicationItem.get(position).getRequiredMonth());
        applicationViewHolder.reqYear.setText(mApplicationItem.get(position).getRequiredYear());
        applicationViewHolder.statusEditText.setText(mApplicationItem.get(position).getStatus());
        applicationViewHolder.residenceIDEditText.setText(Integer.toString(mApplicationItem.get(position).getResidenceID()));
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

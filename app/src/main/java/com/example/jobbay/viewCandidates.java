package com.example.jobbay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Data;
import Model.jobApply;

public class viewCandidates extends AppCompatActivity {

    //recycler

    private RecyclerView recyclerView;

    //firebase

    private DatabaseReference mViewCandidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_candidates);

        recyclerView = findViewById(R.id.recycler_view_candidates);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //Database
        mViewCandidates = FirebaseDatabase.getInstance().getReference().child("Public database");
        mViewCandidates.keepSynced(true);

    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<jobApply> options =
                new FirebaseRecyclerOptions.Builder<jobApply>()
                        .setQuery(mViewCandidates, jobApply.class)
                        .build();

        FirebaseRecyclerAdapter<jobApply, ViewCandidatesViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<jobApply, ViewCandidatesViewHolder>(options) {


                    @NonNull
                    @Override
                    public ViewCandidatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_candiates_posts, parent, false);
                        return new ViewCandidatesViewHolder(v);
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull ViewCandidatesViewHolder holder, int position, @NonNull jobApply model) {
                        holder.setCompanyName(model.getCompanyName());
                        holder.setAdvertName(model.getAdvertname());
                        holder.setEmail(model.getEmailid());
                        holder.setQualification(model.getJobqualificatio());
                        holder.setExperience(model.getExperience());
                        holder.setDate(model.getDate());
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        firebaseRecyclerAdapter.startListening();

    }

    public static class ViewCandidatesViewHolder extends RecyclerView.ViewHolder {

        View myview;


        public ViewCandidatesViewHolder(@NonNull View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setCompanyName(String companyName) {

            TextView mTitle = myview.findViewById(R.id.applyComp);
            mTitle.setText(companyName);
        }

        public void setAdvertName(String advertName) {

            TextView mDate = myview.findViewById(R.id.applyAdvert);
            mDate.setText(advertName);

        }

        public void setEmail(String applyem) {

            TextView mComp = myview.findViewById(R.id.applyEmail);
            mComp.setText(applyem);

        }

        public void setQualification(String qualification) {

            TextView mDesc = myview.findViewById(R.id.applyQual);
            mDesc.setText(qualification);
        }

        public void setExperience(String experience) {

            TextView mSkills = myview.findViewById(R.id.applyExperience);
            mSkills.setText(experience);

        }


        public void setDate(String date) {

            TextView mLocation = myview.findViewById(R.id.applyDate);
            mLocation.setText(date);

        }
    }

}
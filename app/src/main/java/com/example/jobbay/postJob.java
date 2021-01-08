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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Data;

public class postJob extends AppCompatActivity {

    private FloatingActionButton fabBtn;

    private LayoutInflater mInflater;

    //RecyclerView

    private RecyclerView recyclerView;

    //firebase

    private FirebaseAuth mAuth;
    private DatabaseReference JobPostDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();

        JobPostDatabase = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        recyclerView = findViewById(R.id.recycler_job_post_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        fabBtn = findViewById(R.id.fab_add);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),InsertJobPostActivity.class));

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();



        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(JobPostDatabase, Data.class)
                        .build();

        FirebaseRecyclerAdapter<Data,MyViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Data, MyViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Data model) {

                        holder.setJobTitle(model.getTitle());
                        holder.setJobDate(model.getDate());
                        holder.setCompanyName(model.getCompanyName());
                        holder.setJobDescription(model.getDescription());
                        holder.setJobSkills(model.getSkills());
                        holder.setJobSalary(model.getSalary());
                        holder.setLocation(model.getLocation());

                    }

                    @NonNull
                    @Override
                    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_post_item, parent, false);
                        return new MyViewHolder(v);
                    }

                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        firebaseRecyclerAdapter.startListening();


    }





    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setJobTitle(String title){

            TextView mTitle = myview.findViewById(R.id.jobTitle);
            mTitle.setText(title);
        }

        public void setJobDate(String date){

            TextView mDate = myview.findViewById(R.id.JobDate);
            mDate.setText(date);

        }

        public void setCompanyName(String companyName){

            TextView mComp = myview.findViewById(R.id.compName);
            mComp.setText(companyName);

        }

        public void setJobDescription(String description){

            TextView mDesc = myview.findViewById(R.id.jobDesc);
            mDesc.setText(description);
        }

        public void setJobSkills(String skills){

            TextView mSkills = myview.findViewById(R.id.jobSkills);
            mSkills.setText(skills);

        }

        public void setJobSalary(String salary){

            TextView mSalary = myview.findViewById(R.id.jobSalary);
            mSalary.setText(salary);

        }

        public void setLocation(String location){

            TextView mLocation = myview.findViewById(R.id.jobLoc);
            mLocation.setText(location);

        }
    }


}

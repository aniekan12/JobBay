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

public class viewJobs extends AppCompatActivity {

    //recycler

    private RecyclerView recyclerView;

    //firebase

    private DatabaseReference mViewJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        recyclerView = findViewById(R.id.recycler_view_jobs);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        //Database
        mViewJobs = FirebaseDatabase.getInstance().getReference().child("Public database");
        mViewJobs.keepSynced(true);
    }

    @Override
    protected void onStart() {
        super.onStart();



        FirebaseRecyclerOptions<Data> options =
                new FirebaseRecyclerOptions.Builder<Data>()
                        .setQuery(mViewJobs, Data.class)
                        .build();

        FirebaseRecyclerAdapter<Data, ViewJobsViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Data, ViewJobsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewJobsViewHolder holder, int position, @NonNull final Data model) {

                        holder.setJobTitle(model.getTitle());
                        holder.setJobDate(model.getDate());
                        holder.setCompanyName(model.getCompanyName());
                        holder.setJobDescription(model.getDescription());
                        holder.setJobSkills(model.getSkills());
                        holder.setJobSalary(model.getSalary());
                        holder.setLocation(model.getLocation());

                        holder.myview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(getApplicationContext(),JobDetails.class);

                                intent.putExtra("title",model.getTitle());
                                intent.putExtra("date",model.getDate());
                                intent.putExtra("company name",model.getCompanyName());
                                intent.putExtra("description",model.getDescription());
                                intent.putExtra("skills",model.getSkills());
                                intent.putExtra("salary",model.getSalary());
                                intent.putExtra("location",model.getLocation());

                                startActivity(intent);
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public ViewJobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_job_posts, parent, false);
                        return new ViewJobsViewHolder(v);
                    }

                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView.setHasFixedSize(true);
        firebaseRecyclerAdapter.startListening();


    }




    public static class ViewJobsViewHolder extends  RecyclerView.ViewHolder{

        View myview;

        public ViewJobsViewHolder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }

        public void setJobTitle(String title){

            TextView mTitle = myview.findViewById(R.id.jobTitles);
            mTitle.setText(title);
        }

        public void setJobDate(String date){

            TextView mDate = myview.findViewById(R.id.JobDates);
            mDate.setText(date);

        }

        public void setCompanyName(String companyName){

            TextView mComp = myview.findViewById(R.id.compNames);
            mComp.setText(companyName);

        }

        public void setJobDescription(String description){

            TextView mDesc = myview.findViewById(R.id.jobDescs);
            mDesc.setText(description);
        }

        public void setJobSkills(String skills){

            TextView mSkills = myview.findViewById(R.id.jobSkillss);
            mSkills.setText(skills);

        }

        public void setJobSalary(String salary){

            TextView mSalary = myview.findViewById(R.id.jobSalarys);
            mSalary.setText(salary);

        }

        public void setLocation(String location){

            TextView mLocation = myview.findViewById(R.id.jobLocs);
            mLocation.setText(location);

        }

    }
}

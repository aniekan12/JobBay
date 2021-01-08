package com.example.jobbay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class JobDetails extends AppCompatActivity {


    private TextView mTitle;
    private TextView mDate;
    private TextView mCompany;
    private TextView mDescription;
    private TextView mSkills;
    private TextView mSalary;
    private TextView mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);


        mTitle = findViewById(R.id.job_details_title);
        mDate = findViewById(R.id.job_details_date);
        mCompany = findViewById(R.id.job_details_company);
        mDescription = findViewById(R.id.job_details_description);
        mSkills = findViewById(R.id.job_details_skills);
        mSalary = findViewById(R.id.job_details_salary);
        mLocation = findViewById(R.id.job_details_location);


        //Recieve data from all job activity using intent...

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String date =  intent.getStringExtra("date");
        String company = intent.getStringExtra("company name");
        String description = intent.getStringExtra("description");
        String skills = intent.getStringExtra("skills");
        String location = intent.getStringExtra("location");

        mTitle.setText(title);
        mDate.setText(date);
        mCompany.setText(company);
        mDescription.setText(description);
        mSkills.setText(skills);
        mLocation.setText(location);
    }
}

package com.example.jobbay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

import Model.Data;

public class InsertJobPostActivity extends AppCompatActivity {

    private EditText company_name;
    private EditText job_title;
    private EditText job_description;
    private EditText job_skills;
    private EditText salary;
    private EditText location;

    private Button postJob;

    //firebase

    private FirebaseAuth mAuth;
    private DatabaseReference mJobPost;

    private DatabaseReference mPublicDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();

        mJobPost = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        mPublicDatabase = FirebaseDatabase.getInstance().getReference().child("Public database");

        InsertJob();
    }

    private void InsertJob() {

        company_name = findViewById(R.id.company_name);
        job_title = findViewById(R.id.job_title);
        job_description = findViewById(R.id.job_description);
        job_skills = findViewById(R.id.job_skills);
        salary  = findViewById(R.id.job_salary);
        location = findViewById(R.id.job_location);

        postJob = findViewById(R.id.btn_job_post);

        postJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cName = company_name.getText().toString().trim();
                String title = job_title.getText().toString().trim();
                String description = job_description.getText().toString().trim();
                String skills = job_skills.getText().toString().trim();
                String jobSalary = salary.getText().toString().trim();
                String jlocation = location.getText().toString().trim();

                if (TextUtils.isEmpty(cName)) {
                    company_name.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(title)) {
                    job_title.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(description)) {
                    job_description.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(skills)) {
                    job_skills.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(jobSalary)) {
                    salary.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(jlocation)) {
                    location.setError("Required Field!");
                    return;
                }

                String id = mJobPost.push().getKey();

                String date = DateFormat.getDateInstance().format(new Date());

                Data data = new Data(cName,title,description,skills,jobSalary,jlocation,id,date);

                mJobPost.child(id).setValue(data);

                mPublicDatabase.child(id).setValue(data);

                Toast.makeText(getApplicationContext(),"Job Posted",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), com.example.jobbay.postJob.class));
            }
        });
    }
}

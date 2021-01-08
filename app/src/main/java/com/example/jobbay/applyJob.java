package com.example.jobbay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

import Model.Data;
import Model.jobApply;

public class applyJob extends AppCompatActivity {

    TextView company_name, advert_name, contact, emailid, jobqualification, experience;
    Button apply;

    FirebaseAuth mAuth;
    DatabaseReference mApplyJob;

    private DatabaseReference mPublicDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();

        mApplyJob = FirebaseDatabase.getInstance().getReference().child("Apply Job").child(uId);

        mPublicDatabase = FirebaseDatabase.getInstance().getReference().child("Public database");


        ApplyforJob();



    }

    private void ApplyforJob() {
        company_name = findViewById(R.id.tv_name);
        contact = findViewById(R.id.tv_contact);
        emailid = findViewById(R.id.tv_emailid);
        advert_name = findViewById(R.id.tv_JobName);
        jobqualification = findViewById(R.id.tv_jobqualification);
        experience = findViewById(R.id.tv_experience);

        apply = findViewById(R.id.btn_apply);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cName = company_name.getText().toString().trim();
                String Ccontact = contact.getText().toString().trim();
                String Cemailid = emailid.getText().toString().trim();
                String Cadvert = advert_name.getText().toString().trim();
                String jobqual = jobqualification.getText().toString().trim();
                String jexp = experience.getText().toString().trim();

                if (TextUtils.isEmpty(cName)) {
                    company_name.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(Ccontact)) {
                    contact.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(Cemailid)) {
                    emailid.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(Cadvert)) {
                    advert_name.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(jobqual)) {
                    jobqualification.setError("Required Field!");
                    return;
                }

                if (TextUtils.isEmpty(jexp)) {
                    experience.setError("Required Field!");
                    return;
                }

                String id = mApplyJob.push().getKey();

                String date = DateFormat.getDateInstance().format(new Date());

                jobApply data = new jobApply(cName,Ccontact,Cemailid,Cadvert,jobqual,jexp,id,date);

                mApplyJob.child(id).setValue(data);

                mPublicDatabase.child(id).setValue(data);

                Toast.makeText(getApplicationContext(),"Application sent",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), Home.class));

            }
        });
    }
}

package com.example.jobbay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    ImageView viewJobs,showprofile, editprofile, applyforJob,logout;

    //firebase....
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        viewJobs = findViewById(R.id.applied);

        logout = findViewById(R.id.logOut);

        mAuth = FirebaseAuth.getInstance();



        showprofile = findViewById(R.id.showProfile);

        editprofile = findViewById(R.id.editProfile);

        applyforJob = findViewById(R.id.apply);

        viewJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.jobbay.viewJobs.class);
                startActivity(intent);
            }
        });

        applyforJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), applyJob.class);
                startActivity(intent);
            }
        });

        showprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowProfile.class);
                startActivity(intent);
            }
        });

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(intent);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}

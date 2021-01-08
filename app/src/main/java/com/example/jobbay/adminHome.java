package com.example.jobbay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class adminHome extends AppCompatActivity {

    private ImageView post, Candidates, logoutButn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        logoutButn = findViewById(R.id.adminLogout);

        post = findViewById(R.id.postJob);

        Candidates = findViewById(R.id.viewCand);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHome.this,postJob.class);
                startActivity(intent);
            }
        });

        Candidates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminHome.this,viewCandidates.class);
                startActivity(intent);
            }
        });


        logoutButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), adminLogin.class));
            }
        });
    }
}

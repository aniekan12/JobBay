package com.example.jobbay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class adminLogin extends AppCompatActivity {


    private EditText adminemail;
    private EditText adminpassword;

    private Button loginbutn;

    private ImageView logoutButn;

    //firebase....
    private FirebaseAuth mAuth;

    //progress dialog...
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        logoutButn = findViewById(R.id.adminLogout);
        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);
        LoginProcess();

    }

    private void LoginProcess() {

        adminemail = findViewById(R.id.adminuser);
        adminpassword = findViewById(R.id.adminpass);

        loginbutn = findViewById(R.id.adminLogin);

        loginbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mEmail = adminemail.getText().toString().trim();
                String pass = adminpassword.getText().toString().trim();



                if (TextUtils.isEmpty(mEmail)) {
                    adminemail.setError("Required field!!");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    adminpassword.setError("Required field!!");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();



                //admin login
                mAuth.signInWithEmailAndPassword("admin@jobbay.com","administrator").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(),"Welcome, Admin",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),adminHome.class));
                    }
                });

            }
        });


    }
}

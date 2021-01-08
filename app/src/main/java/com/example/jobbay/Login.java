package com.example.jobbay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText password;

    private Button loginbtn;

    private TextView regtext;

    //firebase....
    private FirebaseAuth mAuth;

    //progress dialog...
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),Home.class));
        }

        mDialog = new ProgressDialog(this);
        Login();


    }

    private void Login() {



        email = findViewById(R.id.user);
        password = findViewById(R.id.pass);

        loginbtn = findViewById(R.id.buttonLogin);

        regtext = findViewById(R.id.registertext);

        regtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,adminLogin.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mEmail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();



                if (TextUtils.isEmpty(mEmail)) {
                    email.setError("Required field!!");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    password .setError("Required field!!");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();



                //user login
                mAuth.signInWithEmailAndPassword(mEmail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Welcome to Job bay",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));

                            mDialog.dismiss();
                        }else {
                            Toast.makeText(getApplicationContext(),"Login Failed...",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

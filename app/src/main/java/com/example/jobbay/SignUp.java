package com.example.jobbay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {

    private EditText emaill;
    private EditText Myname;
    private EditText password;
    private EditText rPassword;
    private Button btnReg;

    Uri pickedImgUri;

    ImageView ImgUserPhoto;

    static int REQUESTCODE = 1;

    static int PReqCode = 1;

    private TextView btnLogin;

    //firebase auth

    private FirebaseAuth mAuth;

    //progress dialog
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnLogin = findViewById(R.id.logbtn);

        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

        Registration();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });




    }

    private void Registration() {

        Myname = findViewById(R.id.regName);
        emaill = findViewById(R.id.Email);
        password = findViewById(R.id.Userpassword);
        rPassword = findViewById(R.id.secondpass);





        btnReg = findViewById(R.id.registerbuton);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emaill.getText().toString().trim();
                String usersname = Myname.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String checkpass = rPassword.toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emaill.setError("Please enter valid email");
                    emaill.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(usersname)){
                    emaill.setError("Required field!!");
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    emaill.setError("Required field!!");
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    emaill.setError("Required field!!");
                    return;
                }

                if (pass.length()<6){
                    password.setError("Minimum length should be 6");
                    password.requestFocus();
                    return;
                }

                mDialog.setMessage("Process...");
                mDialog.show();

                //if (!password.equals(rPassword)){
                    //rPassword.setError("Password dont match");
                    //rPassword.requestFocus();
                  //  return;
                //}






                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),"Registration Successful!",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Login.class));
                                mDialog.dismiss();
                            }else {
                                Toast.makeText(getApplicationContext(),"Registration Failed..",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

        });

    }




    }



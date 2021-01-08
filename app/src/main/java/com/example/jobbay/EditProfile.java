package com.example.jobbay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    final static int PICK_PDF_CODE=2342;
    final static int PICK_VIDEO_CODE=2340;

    EditText u_city,u_name,u_address,u_pincode
            ,u_skills,u_achievements,u_certifications,u_workexp;

    ImageView date;
    TextView textViewStatus,u_dob;
    ProgressBar progressBar;

    RadioGroup u_radioGroup;
    RadioButton male,female;

    Spinner ugspinner,pgspinner,workexp_spinner;

    String[] uglist={"Bsc.","BCom","LLB.","BTech"};
    String[] exp={"0 Years","1 Years","2 Years","3 Years","4 Years","5 Years",};
    String[] pglist={"NO","MCA","MCom","MBA","MTech"};
    String[] yearlist={"2000","2001","2002","2003"
            ,"2004","2005","2006","2007","2008"
            ,"2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};



    FirebaseAuth mAuth;

    StorageReference mStorageReference;
    DatabaseReference databaseReference,mDatabasereference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mStorageReference= FirebaseStorage.getInstance().getReference();

        mDatabasereference= FirebaseDatabase.getInstance().getReference((Constants.DATABASE_PATH_UPLOADS+"/"+user.getUid()+"/"));
        databaseReference=FirebaseDatabase.getInstance().getReference();

        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        u_name=(EditText)findViewById(R.id.et_name);
        u_radioGroup=(RadioGroup)findViewById(R.id.rg_sex);
        u_city=(EditText)findViewById(R.id.et_city);
        u_address=(EditText)findViewById(R.id.et_address);

        u_skills=(EditText)findViewById(R.id.et_skills);
        u_achievements=(EditText)findViewById(R.id.et_achievement);
        u_certifications=(EditText)findViewById(R.id.et_certification);

        male=(RadioButton)findViewById(R.id.male);
        male.setChecked(true);
        u_dob=(TextView)findViewById(R.id.et_dob);
        date=(ImageView)findViewById(R.id.date);

        ugspinner=(Spinner) findViewById(R.id.sp_ug);
        pgspinner=(Spinner) findViewById(R.id.sp_pg);
        workexp_spinner=(Spinner)findViewById(R.id.workexp);

        ArrayAdapter<String> ug=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,uglist);
        ugspinner.setAdapter(ug);

        ArrayAdapter<String> pg=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pglist);
        pgspinner.setAdapter(pg);

        ArrayAdapter<String> workexp=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,exp);
        workexp_spinner.setAdapter(workexp);

        findViewById(R.id.btn_saveprofile).setOnClickListener(this);
        findViewById(R.id.cv).setOnClickListener(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(EditProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date=dayOfMonth+"/"+(month+1)+"/"+year;
                        u_dob.setText(date);

                    }
                },year,month,day);
                dialog.show();
            }
        });


    }

    //to upload user data

    private void SaveProfile() {

        FirebaseUser user=mAuth.getCurrentUser();

        String name = u_name.getText().toString().trim().toUpperCase();
        RadioGroup rg = (RadioGroup)findViewById(R.id.rg_sex);
        String sex = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString().toUpperCase();
        String city = u_city.getText().toString().trim().toUpperCase();
        String address = u_address.getText().toString().trim().toUpperCase();
        String ugcourse = ugspinner.getSelectedItem().toString().trim().toUpperCase();
        String pgcourse = pgspinner.getSelectedItem().toString().trim().toUpperCase();
        String skills = u_skills.getText().toString().trim().toUpperCase();
        String achievements = u_achievements.getText().toString().trim().toUpperCase();
        String certifications = u_certifications.getText().toString().trim().toUpperCase();
        String workexp =workexp_spinner.getSelectedItem().toString();
        String email = user.getEmail();
        String dob=u_dob.getText().toString();

        if (name.isEmpty()){
            u_name.setError("Name Required");
            u_name.requestFocus();
            return;
        }
        if (city.isEmpty()){
            u_city.setError("City Required");
            u_city.requestFocus();
            return;
        }
        if (address.isEmpty()){
            u_address.setError("Address Required");
            u_address.requestFocus();
            return;
        }



        if (skills.isEmpty()){
            u_skills.setError("Skills Needed");
            u_skills.requestFocus();
            return;
        }

        if (workexp.isEmpty()){
            u_workexp.setError("Work Experience Needed");
            u_workexp.requestFocus();
            return;
        }
        if (dob.isEmpty()){
            u_dob.setError("DOB Needed");
            u_dob.requestFocus();
            return;
        }

        SaveUserProfile sp=new SaveUserProfile(name,city,address
                ,skills,achievements,certifications,workexp,ugcourse,pgcourse,sex,email,dob);


        databaseReference.child("Users").child(user.getUid()).setValue(sp);
        Toast.makeText(EditProfile.this, "success", Toast.LENGTH_SHORT).show();
        Toast.makeText(EditProfile.this, "Please Upload CV", Toast.LENGTH_SHORT).show();
    }

    ////TO GET PDF FROM STORAGE

    private void getPdf(){

        ////intent for file chooser
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), PICK_PDF_CODE);
    }


    ///THIS WILL UPLOAD PDF FILE
    private void uploadPdfFile(Uri data) {

        progressBar.setVisibility(View.VISIBLE);
        StorageReference sRef = mStorageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + ".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(EditProfile.this, "CV Uploaded", Toast.LENGTH_SHORT).show();

                        UploadCv upload = new UploadCv( taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                        mDatabasereference.child("cv").setValue(upload);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), "Exceeds Upload Size Limit", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        textViewStatus.setText((int) progress + "% Uploading...");
                    }
                });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        //when user choose file
        if (requestCode==PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData()!=null){
            //if a file is selected
            if (data.getData()!= null){
                //uploading file
                uploadPdfFile(data.getData());
            }else {
                Toast.makeText(this, "No File Choosen", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode==PICK_VIDEO_CODE && resultCode == RESULT_OK && data != null && data.getData()!=null){
            //if a file is selected
            if (data.getData()!= null){
                //uploading file
                //uploadVideoFile(data.getData());

            }else {
                Toast.makeText(this, "No File Choosen", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_saveprofile:
                SaveProfile();
                break;

            case R.id.cv:
                getPdf();
                break;
        }
    }
}

package com.example.jobbay;

public class SaveUserProfile {

    public String name,city,address,pincode
            ,xmarks,xyear,xiimarks,xiiyear
            ,ugmarks,ugyear,pgmarks,pgyear
            ,skills,achievements,certifications,workexp,sex,ugcourse,pgcourse,email,dob;

    public SaveUserProfile(String name, String city, String address, String skills, String achievements, String certifications, String workexp, String sex, String ugcourse, String pgcourse, String email, String dob) {
        this.city = city;
        this.name = name;
        this.address = address;
        this.skills = skills;
        this.achievements = achievements;
        this.certifications = certifications;
        this.workexp = workexp;
        this.ugcourse = ugcourse;
        this.pgcourse = pgcourse;
        this.sex = sex;
        this.email=email;
        this.dob=dob;
    }

    public SaveUserProfile(){

    }
}

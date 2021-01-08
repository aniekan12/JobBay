package Model;

import android.widget.Button;

public class jobApply {

    String companyName;
    String contact;
    String emailid;
    String advertname;
    String jobqualificatio;
    String experience;
    String id;
    String date;


    public jobApply(String companyName, String contact, String emailid, String advertname, String jobqualificatio, String experience, String id, String date) {
        this.companyName = companyName;
        this.contact = contact;
        this.emailid = emailid;
        this.advertname = advertname;
        this.jobqualificatio = jobqualificatio;
        this.experience = experience;
        this.id = id;
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getAdvertname() {
        return advertname;
    }

    public void setAdvertname(String advertname) {
        this.advertname = advertname;
    }

    public String getJobqualificatio() {
        return jobqualificatio;
    }

    public void setJobqualificatio(String jobqualificatio) {
        this.jobqualificatio = jobqualificatio;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public jobApply(){


    }
}

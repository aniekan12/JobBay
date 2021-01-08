package Model;

public class Data {

    String companyName;
    String title;
    String description;
    String skills;
    String salary;
    String location;
    String id;
    String date;

    public Data(String companyName, String title, String description, String skills, String salary, String location, String id, String date) {
        this.companyName = companyName;
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.salary = salary;
        this.location = location;
        this.id = id;
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public Data(){


    }
}

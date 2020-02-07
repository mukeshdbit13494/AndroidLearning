package com.example.phoneauth;

public class Users {
    public String id;
    public String name;
    public String number;
    public String email;
    public String gender;
    public String dob;
    public String goal;
    public String acept;

    public Users(){

    }

    public Users(String id,String name, String number, String email, String gender, String dob, String goal, String acept) {
        this.id=id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.goal = goal;
        this.acept = acept;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getGoal() {
        return goal;
    }

    public String getAcept() {
        return acept;
    }
}

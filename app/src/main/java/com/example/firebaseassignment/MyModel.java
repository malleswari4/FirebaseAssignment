package com.example.firebaseassignment;

public class MyModel {

    String name,roll,phone,email;

    public MyModel() {

    }

    public MyModel(String name, String roll, String phone, String email) {
        this.name = name;
        this.roll = roll;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public String getRoll() {

        return roll;
    }

    public String getPhone() {

        return phone;
    }

    public String getEmail()
    {
        return email;
    }
}

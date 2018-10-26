package com.example.sherin.sqlite_patient_management_project;

public class Patient {

    private String id;
    private String name;
    private String add;
    private String number;
    private String admit;
    private  String issu;


    public Patient(String id, String name, String add, String number,String admit,String issu) {
        this.id = id;
        this.name = name;
        this.add = add;
        this.number = number;
        this.admit=admit;
        this.issu=issu;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAdd() {
        return add;
    }

    public String getNumber() {
        return number;
    }

    public String getAdmit() {
        return admit;
    }

    public String getIssu() {
        return issu;
    }
}
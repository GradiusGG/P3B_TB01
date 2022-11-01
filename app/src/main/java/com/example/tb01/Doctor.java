package com.example.tb01;

import java.io.Serializable;

public class Doctor implements Serializable {
    private String name;
    private String specialization;
    private String phone;
    private long id;

    public Doctor(long id, String name, String specialization, String phone){
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {
        return "dr. "+name;
    }

    public String getPhone() {
        return phone;
    }

    public long getId() {
        return id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return String.format("%d. dr. %s", this.id, this.name);
    }
}

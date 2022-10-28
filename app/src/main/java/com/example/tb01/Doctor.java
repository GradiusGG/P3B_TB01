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

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return String.format("%d. dr. %s", this.id, this.name);
    }
}

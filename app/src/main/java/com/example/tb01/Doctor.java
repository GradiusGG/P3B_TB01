package com.example.tb01;

public class Doctor {
    private String name;
    private String specialization;

    public Doctor(String name, String specialization){
        this.name = name;
        this.specialization = specialization;
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

    @Override
    public String toString(){
        return "dr. "+this.name;
    }
}

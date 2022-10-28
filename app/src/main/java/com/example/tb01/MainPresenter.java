package com.example.tb01;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class MainPresenter {
    private MainUI ui;
    private RSCepatKembaliDBHelper DBHelper;
    private ArrayList<Doctor> doctorList;
    public static String[] navMenuArray = {"Home", "Pertemuan", "Dokter", "Exit"};

    public MainPresenter(MainUI ui){
        this.ui = ui;
        this.DBHelper = new RSCepatKembaliDBHelper(this.ui.getContext());
        this.doctorList = this.DBHelper.getAllDoctors();
    }

    public void setEtDate(int year, int month, int dayOfMonth){
        String dateString = String.format("%02d/$02d/%04d", dayOfMonth, month, year);
        this.ui.setAppointmentDate(dateString);
    }
    
    public void setEtTime(int hourOfDay, int minute){
        String timeString = String.format("%02d:%02d", hourOfDay, minute);
        this.ui.setAppointmentTime(timeString);
    }

    //Doctor
    public void addDoctorData(String name, String specialization) {
        long id = this.DBHelper.insertDoctor(name, specialization);
        if(id > -1){
            Doctor d = this.DBHelper.getDoctorData(id);
            this.doctorList.add(d);
            this.ui.updateDoctorList(this.doctorList);
        }
    }

    public Context getMainUIContext(){
        return this.ui.getContext();
    }

    public String getDoctorName(int idx){
        return this.doctorList.get(idx).getName();
    }

    public String getDoctorSpec(int idx) {
        return this.doctorList.get(idx).getSpecialization();
    }

    public void updateDoctorList() {
        this.ui.updateDoctorList(this.doctorList);
    }

    public ArrayList<Doctor> getDoctorList() {
        return this.doctorList;
    }

    public interface MainUI{
        Context getContext();

        void setAppointmentDate(String text);

        void setAppointmentTime(String text);

        void updateDoctorList(ArrayList<Doctor> doctors);
    }
}

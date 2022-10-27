package com.example.tb01;

import java.util.ArrayList;

public class MainPresenter {
    private MainUI ui;
    public static String[] navMenuArray = {"Home", "Pertemuan", "Dokter", "Exit"};
    private ArrayList<Doctor> doctorList;

    public MainPresenter(MainUI ui){
        this.ui = ui;
        this.doctorList = new ArrayList<>();
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
        this.doctorList.add(new Doctor(name, specialization));

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

    public interface MainUI{
        void setAppointmentDate(String text);

        void setAppointmentTime(String text);

        void updateDoctorList(ArrayList<Doctor> doctors);
    }
}

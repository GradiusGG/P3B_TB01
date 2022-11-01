package com.example.tb01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import java.util.ArrayList;

public class MainPresenter {
    private MainUI ui;
    private RSCepatKembaliDBHelper DBHelper;
    private ArrayList<Doctor> doctorList;
    private ArrayList<Appointment> appointmentList;
    public static String[] navMenuArray = {"Home", "Pertemuan", "Daftar Pertemuan", "Dokter", "Exit"};

    public MainPresenter(MainUI ui){
        this.ui = ui;
        this.DBHelper = new RSCepatKembaliDBHelper(this.ui.getContext());
        this.doctorList = this.DBHelper.getAllDoctors();
        this.appointmentList = this.DBHelper.getAllAppointments();
    }

    // =============================================================================================
    //Doctor
    public void addDoctorData(String name, String specialization, String phone) {
        String title;
        String message;
        int iconId;

        long id = this.DBHelper.insertDoctor(name, specialization, phone);
        if(id > -1){
            Doctor d = this.DBHelper.getDoctorData(id);
            this.doctorList.add(d);
            this.ui.updateDoctorList(this.doctorList);

            title = "Data Berhasil Ditambahkan";
            message = "Data dokter berhasil ditambahkan ke basis data.";
            iconId = android.R.drawable.ic_dialog_info;
        } else {
            title = "Terjadi Kesalahan";
            message = "Data dokter gagal ditambahkan ke basis data.";
            iconId = android.R.drawable.ic_dialog_alert;
        }
        this.ui.showAlertAddDoctorDialog(title, message, iconId);
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

    // =============================================================================================
    // Appointment
    public ArrayList<Doctor> getDoctorList() {
        return this.doctorList;
    }

    public String getAppointmentDate(int idx) {
        return this.appointmentList.get(idx).getDate();
    }

    public String getAppointmentTime(int idx) {
        return this.appointmentList.get(idx).getTime();
    }

    public String getAppointmentDateTime(int idx){
        Appointment a = this.appointmentList.get(idx);
        return a.getDate()+" "+a.getTime();
    }

    public String getAppointmentDoctorName(int idx){
        return this.appointmentList.get(idx).getDoctorName();
    }

    public String getAppointmentPatient(int idx){
        return this.appointmentList.get(idx).getPatientName();
    }

    public String getAppointmentDoctorPhone(int idx){
        int idDoctor = (int) this.appointmentList.get(idx).getDoctorId();
        return this.doctorList.get(idDoctor-1).getPhone();
    }

    public String getAppointmentComplaints(int idx){
        return this.appointmentList.get(idx).getComplaints();
    }

    public void updateAppointmentList() {
        this.ui.updateAppointmentList(this.appointmentList);
    }

    public void addAppointmentData(String patientName, String date, String time, String complaints,
                                   long doctorId) {
        String title;
        String message;
        int iconId;

        long id = this.DBHelper.insertAppointment(patientName, complaints, date, time, doctorId);
        if(id > -1){
            Appointment a = this.DBHelper.getAppointmentData(id);
            this.appointmentList.add(a);
            this.ui.updateAppointmentList(this.appointmentList);

            title = "Data Berhasil Ditambahkan";
            message = "Data pertemuan berhasil ditambahkan ke basis data.";
            iconId = android.R.drawable.ic_dialog_info;
        } else {
            title = "Terjadi Kesalahan";
            message = "Data pertemuan gagal ditambahkan ke basis data.";
            iconId = android.R.drawable.ic_dialog_alert;
        }
        this.ui.showAlertAddAppointmentDialog(title, message, iconId);
    }

    public void setEtDate(int year, int month, int dayOfMonth){
        String dateString = String.format("%02d/%02d/%04d", dayOfMonth, month, year);
        this.ui.setAppointmentDate(dateString);
    }

    public void setEtTime(int hourOfDay, int minute){
        String timeString = String.format("%02d:%02d", hourOfDay, minute);
        this.ui.setAppointmentTime(timeString);
    }

    public void requestAppointmentDialog(int i) {
        this.ui.showAppointMentDialog(i);
    }

    // =============================================================================================

    public interface MainUI{
        Context getContext();

        void setAppointmentDate(String text);

        void setAppointmentTime(String text);

        void updateDoctorList(ArrayList<Doctor> doctors);

        void updateAppointmentList(ArrayList<Appointment> appointmentList);

        void showAlertAddDoctorDialog(String title, String message, int iconId);

        void showAlertAddAppointmentDialog(String title, String message, int iconId);

        void showAppointMentDialog(int i);
    }
}

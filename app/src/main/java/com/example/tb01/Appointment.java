package com.example.tb01;

public class Appointment {
    private String patientName;
    private String doctorName;
    private String date;
    private String time;
    private String complaints;
    private long appointId;
    private long doctorId;

    public Appointment(String patientName, String doctorName, String date, String time, String
            complaints, long appointId, long doctorId){
        this.patientName = patientName;
        this.date = date;
        this.time = time;
        this.complaints = complaints;
        this.appointId = appointId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getComplaints() {
        return complaints;
    }

    public long getAppointId() {
        return appointId;
    }

    public long getDoctorId() {
        return doctorId;
    }
}

package com.example.tb01;

public class MainPresenter {
    private MainUI ui;
    public static String[] navMenuArray = {"Home", "Pertemuan ", "Dokter", "Exit"};

    public MainPresenter(MainUI ui){
        this.ui = ui;
    }

    public void setEtDate(int year, int month, int dayOfMonth){
        String dateString = String.format("%02d/$02d/%04d", dayOfMonth, month, year);
        this.ui.setAppointmentDate(dateString);
    }
    
    public void setEtTime(int hourOfDay, int minute){
        String timeString = String.format("%02d:%02d", hourOfDay, minute);
        this.ui.setAppointmentTime(timeString);
    }

    public interface MainUI{
        void setAppointmentDate(String text);

        void setAppointmentTime(String text);
    }
}

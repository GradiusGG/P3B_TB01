package com.example.tb01;

public class MainPresenter {
    private MainUI ui;

    public MainPresenter(MainUI ui){
        this.ui = ui;
    }

    public void setEtDate(int year, int month, int dayOfMonth){
        String dateString = dayOfMonth+"/"+month+"/"+year;
        this.ui.setAppointmentDate(dateString);
    }

    public interface MainUI{
        void setAppointmentDate(String text);
    }
}

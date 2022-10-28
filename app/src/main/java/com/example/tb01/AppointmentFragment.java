package com.example.tb01;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tb01.databinding.FragmentAppointmentBinding;

import java.util.Calendar;
import java.util.Date;

public class AppointmentFragment extends Fragment {
    private FragmentAppointmentBinding binding;
    private FragmentManager manager;
    private String[] doctorNames = {"Doctor1", "Doctor2", "Doctor3"};
    private MainPresenter presenter;

    public AppointmentFragment() {
    }

    public static AppointmentFragment newInstance(MainPresenter presenter) {
        AppointmentFragment fragment = new AppointmentFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAppointmentBinding.inflate(inflater, container, false);
        this.manager = this.getParentFragmentManager();

        View view = this.binding.getRoot();

        Spinner doctorSelect = this.binding.spinnerDoctor;
        ArrayAdapter<Doctor> adapter = new ArrayAdapter<>(doctorSelect.getContext(),
                android.R.layout.simple_spinner_dropdown_item, this.presenter.getDoctorList());
        doctorSelect.setAdapter(adapter);

        // Listeners
        this.binding.etDate.setOnClickListener(this::onDateClick);
        this.binding.etTime.setOnClickListener(this::onTimeClick);
        this.binding.btnSave.setOnClickListener(this::onSaveClick);

        return view;
    }

    private void onDateClick(View view) {
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH);
        int currDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this.getContext(), this::onDateSet, currYear,
                currMonth, currDay);
        dialog.show();
    }

    private void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.presenter.setEtDate(year, month, dayOfMonth);
    }

    private void onTimeClick(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(this.getContext(), this::onTimeSet, hour,
                minute, true);
        dialog.show();
    }

    private void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.presenter.setEtTime(hourOfDay, minute);
    }

    public void onSaveClick(View view) {
        String name = this.binding.etPatientName.getText().toString();
        String complaints = this.binding.etComplaint.getText().toString();
        String date = this.binding.etDate.getText().toString();
        String time = this.binding.etTime.getText().toString();
        String doctor = this.binding.spinnerDoctor.getSelectedItem().toString();

        int idDoctor = Integer.parseInt(doctor.substring(0, doctor.indexOf(".")));

        this.presenter.saveAppointment(name, complaints, date, time, idDoctor);
    }

    public void setEtDate(String text) {
        this.binding.etDate.setText(text);
    }

    public void setEtTime(String text) {
        this.binding.etTime.setText(text);
    }
}

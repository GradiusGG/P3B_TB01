package com.example.tb01;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

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

    public AppointmentFragment(){}

    public static AppointmentFragment newInstance(MainPresenter presenter){
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(doctorSelect.getContext(),
                android.R.layout.simple_spinner_dropdown_item, doctorNames);
        doctorSelect.setAdapter(adapter);

        // Listeners
        this.binding.etDate.setOnClickListener(this::onDateClick);

        return view;
    }

    private void onDateClick(View view){
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH);
        int currDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(), this::onDateSet,
                currYear, currMonth, currDay);
        datePickerDialog.show();
    }

    private void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
        this.presenter.setEtDate(year, month, dayOfMonth);
    }

    public void setEtDate(String text){
        this.binding.etDate.setText(text);
    }
}

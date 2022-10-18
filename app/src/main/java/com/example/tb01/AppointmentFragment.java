package com.example.tb01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tb01.databinding.FragmentAppointmentBinding;

public class AppointmentFragment extends Fragment {
    private FragmentAppointmentBinding binding;
    private FragmentManager manager;
    private String[] doctorNames = {"Doctor1", "Doctor2", "Doctor3"};

    public AppointmentFragment(){}

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

        return view;
    }


}

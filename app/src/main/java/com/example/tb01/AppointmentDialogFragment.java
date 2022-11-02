package com.example.tb01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tb01.databinding.FragmentAppointmentDialogBinding;

public class AppointmentDialogFragment extends DialogFragment {
    private MainPresenter presenter;
    private FragmentAppointmentDialogBinding binding;

    public AppointmentDialogFragment() {
    }

    public static AppointmentDialogFragment newInstance(MainPresenter presenter, int idAppointment) {
        Bundle args = new Bundle();
        args.putInt("idAppointment", idAppointment);
        AppointmentDialogFragment fragment = new AppointmentDialogFragment();
        fragment.setArguments(args);
        fragment.presenter = presenter;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.binding = FragmentAppointmentDialogBinding.inflate(inflater,container,false);

        Bundle args = this.getArguments();
        int idAppointment = args.getInt("idAppointment");

        this.binding.tvTime.setText(this.presenter.getAppointmentDateTime(idAppointment));
        this.binding.tvPatient.setText(this.presenter.getAppointmentPatient(idAppointment));
        this.binding.tvDoctor.setText(this.presenter.getAppointmentDoctorName(idAppointment));
        this.binding.tvComplaints.setText(this.presenter.getAppointmentComplaints(idAppointment));
        this.binding.tvPhone.setText(this.presenter.getAppointmentDoctorPhone(idAppointment));

        this.binding.btnCall.setOnClickListener(this::onCallClick);
        this.binding.btnClose.setOnClickListener(this::onCloseClick);

        return binding.getRoot();
    }

    private void onCloseClick(View view) {
        this.dismiss();
    }

    private void onCallClick(View view){
        Bundle args = this.getArguments();
        int idAppointment = args.getInt("idAppointment");
        this.presenter.makeCall(idAppointment);
    }
}

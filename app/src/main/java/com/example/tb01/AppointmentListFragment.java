package com.example.tb01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tb01.databinding.FragmentAppointmentListBinding;

import java.util.ArrayList;

public class AppointmentListFragment extends Fragment {
    private MainPresenter presenter;
    private FragmentAppointmentListBinding binding;
    private AppointmentListAdapter adapter;
    private FragmentManager manager;

    public AppointmentListFragment(){};

    public static AppointmentListFragment newInstance(MainPresenter presenter) {
        AppointmentListFragment fragment = new AppointmentListFragment();
        fragment.presenter = presenter;
        fragment.adapter = new AppointmentListAdapter(presenter);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        this.binding = FragmentAppointmentListBinding.inflate(inflater,container,false);
        this.manager = getParentFragmentManager();

        // Atur & populate listview
        ListView listAppointment = this.binding.listAppointment;
        listAppointment.setAdapter(this.adapter);
        this.presenter.updateAppointmentList();

        // Listener
        listAppointment.setOnItemClickListener(this::onItemClick);

        return binding.getRoot();
    }

    private void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.presenter.requestAppointmentDialog(i);
    }

    public void updateAppointmentList(ArrayList<Appointment> appointmentList) {
        this.adapter.addLine(appointmentList);
    }
}

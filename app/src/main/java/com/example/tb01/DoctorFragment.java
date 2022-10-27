package com.example.tb01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tb01.databinding.FragmentDoctorBinding;

import java.util.ArrayList;

public class DoctorFragment extends Fragment {
    private MainPresenter presenter;
    private FragmentDoctorBinding binding;
    private DoctorListAdapter adapter;
    private FragmentManager manager;

    public DoctorFragment(){}

    public static DoctorFragment newInstance(MainPresenter presenter) {
        DoctorFragment fragment = new DoctorFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        this.binding = FragmentDoctorBinding.inflate(inflater,container,false);
        this.manager = getParentFragmentManager();

        // Atur & populate listview
        this.adapter = new DoctorListAdapter(this.presenter);
        this.binding.listDoctor.setAdapter(this.adapter);
        this.presenter.updateDoctorList();

        // Listener
        this.binding.fabAdd.setOnClickListener(this::onBtnAddClick);

        return binding.getRoot();
    }

    private void onBtnAddClick(View view) {

    }

    public void updateDoctorList(ArrayList<Doctor> doctorList){
        this.adapter.addLine(doctorList);
    }
}

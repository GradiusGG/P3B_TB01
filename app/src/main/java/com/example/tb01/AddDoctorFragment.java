package com.example.tb01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tb01.databinding.FragmentAddDoctorBinding;

public class AddDoctorFragment extends Fragment {
    private MainPresenter presenter;
    private FragmentAddDoctorBinding binding;
    private FragmentManager manager;

    public AddDoctorFragment(){}

    public static AddDoctorFragment newInstance(MainPresenter presenter) {
        AddDoctorFragment fragment = new AddDoctorFragment();
        fragment.presenter = presenter;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        this.binding = FragmentAddDoctorBinding.inflate(inflater,container,false);
        this.manager = getParentFragmentManager();

        this.binding.btnAdd.setOnClickListener(this::onAddClick);

        return binding.getRoot();
    }

    private void onAddClick(View view){
        String name = this.binding.etDoctorName.getText().toString();
        String specialization = this.binding.etDoctorSpec.getText().toString();
        String phone = this.binding.etDoctorPhone.getText().toString();
        this.presenter.addDoctorData(name, specialization, phone);
    }


}
package com.example.tb01;

import android.os.Bundle;
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

        return binding.getRoot();
    }
}
package com.example.tb01;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tb01.databinding.FragmentDateBinding;

public class DateFragment extends DialogFragment {
    private FragmentDateBinding binding;

    public DateFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentDateBinding.inflate(inflater,container,false);
        Bundle args = this.getArguments();
        return binding.getRoot();
    }
}

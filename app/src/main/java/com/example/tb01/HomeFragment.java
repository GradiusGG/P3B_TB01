package com.example.tb01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tb01.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private FragmentManager manager;

    public HomeFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentHomeBinding.inflate(inflater, container, false);
        this.manager = this.getParentFragmentManager();

        View view = this.binding.getRoot();

        this.binding.btnAppointment.setOnClickListener(this::onClick);

        return view;
    }

    private void onClick(View view){
        Bundle result = new Bundle();
        result.putString("page", "pertemuan");
        Log.d("debug", "method onclik homefrag: "+result.get("page"));
        this.manager.setFragmentResult("changePage", result);
    }
}

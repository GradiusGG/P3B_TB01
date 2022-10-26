package com.example.tb01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tb01.databinding.FragmentLeftDrawerBinding;

public class LeftDrawerFragment extends Fragment {
    private FragmentLeftDrawerBinding binding;
    private FragmentManager manager;

    public LeftDrawerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentLeftDrawerBinding.inflate(inflater,container,false);
        View view = this.binding.getRoot();

//        String[] list = {"Home", "Page 2", "Exit"};
//        this.adapter = new DrawerListAdapter(this.getActivity());
//        this.adapter.initList(list);
//        this.binding.listDrawer.setAdapter(adapter);
        return view;
    }
}

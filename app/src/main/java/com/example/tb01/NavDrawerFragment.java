package com.example.tb01;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tb01.databinding.FragmentNavDrawerBinding;


public class NavDrawerFragment extends Fragment {
    private FragmentNavDrawerBinding binding;
    private FragmentManager manager;

    public NavDrawerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inisialisasi atribut
        this.binding = FragmentNavDrawerBinding.inflate(inflater, container, false);
        this.manager = getParentFragmentManager();

        // Populate list
        ListView navlist = this.binding.listDrawer;
        navlist.setAdapter((new ArrayAdapter<String>(this.getContext(), R.layout.item_nav,
                MainPresenter.navMenuArray)));

        // Click listener
        navlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                String page = tv.getText().toString().toLowerCase();
                Bundle bundle = new Bundle();
                bundle.putString("page", page);
                Log.d("debug", page);
                manager.setFragmentResult("changePage", bundle);
            }
        });

        View view = this.binding.getRoot();
        return view;
    }
}

package com.example.tb01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.tb01.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainUI {
    private ActivityMainBinding binding;
    private HashMap<String, Fragment> fragments;
    private FragmentManager manager;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inisialisasi atribut
        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        this.fragments = new HashMap<>();
        this.manager = this.getSupportFragmentManager();
        this.presenter = new MainPresenter(this);

        setContentView(this.binding.getRoot());

        // Buat fragments
        /* Beberapa key dalam bahasa indonesia supaya perpindahan page lebih mudah (hubungannya dengan
           text pada navigator drawer)
         */
        this.fragments.put("home", new HomeFragment());
        this.fragments.put("pertemuan", AppointmentFragment.newInstance(this.presenter));
        this.fragments.put("dokter", DoctorFragment.newInstance(this.presenter));
        this.fragments.put("add", AddDoctorFragment.newInstance(this.presenter));

        // Drawer
        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, this.binding.drawerLayout,
                this.binding.toolbar, R.string.open_drawer, R.string.close_drawer);
        this.binding.drawerLayout.addDrawerListener(abdt);
        abdt.syncState();

        // Listener
        this.manager.setFragmentResultListener("changePage", this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String page = result.getString("page");
                        changePage(page);
                    }
                });

        // Set halaman pertama
        this.changePage("home");
    }

    public void changePage(String page) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        try {
            ft.replace(this.binding.fragmentContainer.getId(), this.fragments.get(page));
        } catch (NullPointerException e) {
            new AlertDialog.Builder(this)
                    .setTitle("Halaman Tidak Ditemukan")
                    .setMessage("Halaman yang di-request tidak ditemukan.")
                    .setPositiveButton("OK", null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        ft.commit();

//        Fragment fragmentHome = this.fragments.get("home");
//        Fragment fragmentAppointment = this.fragments.get("pertemuan");
//        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//        if (page == 1) {
//            if (fragmentHome.isAdded()) {
//                ft.show(fragmentHome);
//            } else {
//                ft.add(this.binding.fragmentContainer.getId(), fragmentHome);
//            }
//
//            if (fragmentAppointment.isAdded()) {
//                ft.hide(fragmentAppointment);
//            }
//        } else if (page == 2) {
//            if (fragmentAppointment.isAdded()) {
//                ft.show(fragmentAppointment);
//            } else {
//                ft.add(this.binding.fragmentContainer.getId(), fragmentAppointment).addToBackStack(null);
//            }
//
//            if (fragmentHome.isAdded()) {
//                ft.hide(fragmentHome);
//            }
//        }

    }

    @Override
    public void setAppointmentDate(String text) {
        AppointmentFragment fragment = (AppointmentFragment) this.fragments.get("pertemuan");
        fragment.setEtDate(text);
    }

    @Override
    public void setAppointmentTime(String text) {
        AppointmentFragment fragment = (AppointmentFragment) this.fragments.get("pertemuan");
        fragment.setEtTime(text);
    }

    @Override
    public void updateDoctorList(ArrayList<Doctor> doctors) {
        DoctorFragment fragment = (DoctorFragment) this.fragments.get("dokter");
        fragment.updateDoctorList(doctors);
    }
}
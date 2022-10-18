package com.example.tb01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tb01.databinding.ActivityMainBinding;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private HashMap<String, Fragment> fragments;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inisialisasi
        this.binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        this.fragments = new HashMap<>();
        this.manager = this.getSupportFragmentManager();

        setContentView(R.layout.activity_main);

        // Buat fragments
        this.fragments.put("home", new HomeFragment());
        this.fragments.put("appointment", new AppointmentFragment());

        // Listener
        this.manager.setFragmentResultListener("changePage", this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        int page = result.getInt("page");
                        changePage(page);
                    }
                });

        // Set halaman pertama
        this.changePage(1);
    }

    public void changePage(int page){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragmentHome = this.fragments.get("home");
        Fragment fragmentAppointment = this.fragments.get("appointment");
        if(page == 1){
            if(fragmentHome.isAdded()){
                ft.show(fragmentHome);
            }else{
                ft.add(this.binding.fragmentContainer.getId(), fragmentHome);
            }

            if(fragmentAppointment.isAdded()){
                ft.hide(fragmentAppointment);
            }
        }else if(page == 2){
            if(fragmentAppointment.isAdded()){
                ft.show(fragmentAppointment);
            }else{
                ft.add(this.binding.fragmentContainer.getId(), fragmentAppointment).addToBackStack(null);
            }

            if(fragmentHome.isAdded()){
                ft.hide(fragmentHome);
            }
        }
        ft.commit();
    }
}
package com.example.tb01;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tb01.databinding.ItemAppointmentListBinding;

import java.util.ArrayList;

public class AppointmentListAdapter extends BaseAdapter {
    private ArrayList<Appointment> appointmentList;
    private MainPresenter presenter;

    public AppointmentListAdapter(MainPresenter presenter){
        this.appointmentList = new ArrayList<>();
        this.presenter = presenter;
    }

    private class ViewHolder {
        private final TextView tvDoctor;
        private final TextView tvPatient;
        private final TextView tvDate;
        private final MainPresenter presenter;

        public ViewHolder(ItemAppointmentListBinding binding, MainPresenter presenter) {
            this.presenter = presenter;
            this.tvDate = binding.tvDate;
            this.tvDoctor = binding.tvDoctor;
            this.tvPatient = binding.tvPatient;
        }

        public void updateView(int idx) {
            String date = this.presenter.getAppointmentDate(idx);
            String doctor = this.presenter.getAppointmentDoctorName(idx);
            String patient = this.presenter.getAppointmentPatient(idx);
            this.tvDate.setText(date);
            this.tvDoctor.setText(doctor);
            this.tvPatient.setText(patient);
        }
    }

    @Override
    public int getCount() {
        return this.appointmentList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.appointmentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater;
        ItemAppointmentListBinding binding;
        ViewHolder viewHolder;

        if (view == null){
            inflater = LayoutInflater.from(viewGroup.getContext());
            binding = ItemAppointmentListBinding.inflate(inflater);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding, this.presenter);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(i);

        return view;
    }

    public void addLine(ArrayList<Appointment> appointments) {
        this.appointmentList = appointments;
        this.notifyDataSetChanged();
    }
}

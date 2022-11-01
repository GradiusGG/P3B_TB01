package com.example.tb01;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tb01.databinding.FragmentDoctorBinding;
import com.example.tb01.databinding.ItemDoctorListBinding;

import java.util.ArrayList;

public class DoctorListAdapter extends BaseAdapter {
    private ArrayList<Doctor> doctorList;
    private MainPresenter presenter;

    public DoctorListAdapter(MainPresenter presenter) {
        this.doctorList = new ArrayList<Doctor>();
        this.presenter = presenter;
    }

    private class ViewHolder {
        private final TextView tvName;
        private final TextView tvSpecialization;
        private final MainPresenter presenter;

        public ViewHolder(ItemDoctorListBinding binding, MainPresenter presenter) {
            this.presenter = presenter;
            this.tvName = binding.tvName;
            this.tvSpecialization = binding.tvSpecialization;
        }

        public void updateView(int idx) {
            String name = this.presenter.getDoctorName(idx);
            String specialization = this.presenter.getDoctorSpec(idx);
            this.tvName.setText(name);
            this.tvSpecialization.setText(String.format("%s %s", this.presenter.getMainUIContext()
                    .getString(R.string.tv_spec_text), specialization));
        }
    }

    @Override
    public int getCount() {
        return this.doctorList.size();
    }

    @Override
    public Doctor getItem(int i) {
        return this.doctorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater;
        ItemDoctorListBinding binding;
        ViewHolder viewHolder;

        if (view == null){
            inflater = LayoutInflater.from(viewGroup.getContext());
            binding = ItemDoctorListBinding.inflate(inflater);
            view = binding.getRoot();
            viewHolder = new ViewHolder(binding, this.presenter);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.updateView(i);

        return view;
    }

    public void addLine(ArrayList<Doctor> listDoctor) {
        this.doctorList = listDoctor;
        this.notifyDataSetChanged();
    }
}

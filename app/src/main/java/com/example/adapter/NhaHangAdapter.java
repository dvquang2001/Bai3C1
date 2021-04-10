package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bai3c1.R;
import com.example.modl.NhaHang;

public class NhaHangAdapter extends ArrayAdapter<NhaHang> {
    Activity context;
    int resource;
    public NhaHangAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = this.context.getLayoutInflater().inflate(this.resource,null);
        ImageView imgHinh = customView.findViewById(R.id.imgHinh);
        TextView txtTen = customView.findViewById(R.id.txtTen);
        TextView txtSdt = customView.findViewById(R.id.txtSdt);
        NhaHang nh = getItem(position);
        imgHinh.setImageResource(nh.getHinh());
        txtSdt.setText(nh.getSdt());
        txtTen.setText(nh.getTen());
        return customView;
    }
}

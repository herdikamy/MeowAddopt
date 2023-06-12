package com.example.meowaddopt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    Context context;
    int[] gambar;
    String[] namanya;
    LayoutInflater inflter;

    public GridAdapter(Context applicationContext, int[] gambar, String[] namanya) {
        this.context = applicationContext;
        this.gambar = gambar;
        this.namanya = namanya;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return gambar.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflter.inflate(R.layout.custom_grid, null);
        }
//        ImageView gambarHewan = view.findViewById(R.id.gambar);
//        TextView namaHewan = view.findViewById(R.id.nama);

//        namaHewan.setText(namanya[i]);
//        gambarHewan.setImageResource(gambar[i]);
        return view;
    }
}

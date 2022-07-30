package com.example.ecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.model.LoaiSP;

import java.util.ArrayList;

public class LoaiSPAdapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiSP>arr;

    public LoaiSPAdapter(Context context, ArrayList<LoaiSP> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTenLoaiSP;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null) {
            holder=new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_listview_loaisp, null);
            holder.txtTenLoaiSP=view.findViewById(R.id.txtTenLoaiSP);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }
        LoaiSP loaiSP=arr.get(i);
        holder.txtTenLoaiSP.setText(loaiSP.getTen());
        return view;
    }
}

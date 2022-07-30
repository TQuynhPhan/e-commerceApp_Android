package com.example.ecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.activity.GioHangActivity;
import com.example.ecommerceapp.activity.MainActivity;
import com.example.ecommerceapp.model.GioHang;
import com.example.ecommerceapp.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {

    Context context;
    ArrayList<GioHang>arr;

    public GioHangAdapter(Context context, ArrayList<GioHang> arr) {
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
        CheckBox chkGH;
        ImageView imgGH;
        TextView txtTen, txtGia;
        Button btnSubtract, btnValue, btnAdd, btnDel;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.row_listview_gio_hang, null);
            holder.chkGH=view.findViewById(R.id.chkGH);
            holder.imgGH=view.findViewById(R.id.imgGH);
            holder.txtTen=view.findViewById(R.id.txtTenGH);
            holder.txtGia=view.findViewById(R.id.txtGiaGH);
            holder.btnSubtract=view.findViewById(R.id.btnSubtractGH);
            holder.btnValue=view.findViewById(R.id.btnValueGH);
            holder.btnAdd=view.findViewById(R.id.btnAddGH);
            holder.btnDel=view.findViewById(R.id.btnDelGH);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }
        GioHang gioHang=arr.get(i);
        holder.txtTen.setText(gioHang.getTenSP());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtGia.setText(decimalFormat.format(gioHang.getGiaSP())+" đ");
        Picasso.get().load(gioHang.getHinhAnhSP())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_error)
                .into(holder.imgGH);
        holder.btnValue.setText(String.valueOf(gioHang.getSoLuongSP()));
        int soLuongSPHT=Integer.parseInt(holder.btnValue.getText().toString());
        if(soLuongSPHT<=1){
            holder.btnSubtract.setVisibility(View.INVISIBLE);
            holder.btnAdd.setVisibility(View.VISIBLE);
        }
        else if(soLuongSPHT>=20){
            holder.btnAdd.setVisibility(View.INVISIBLE);
            holder.btnSubtract.setVisibility(View.VISIBLE);
        }
        else{
            holder.btnAdd.setVisibility(View.VISIBLE);
            holder.btnSubtract.setVisibility(View.VISIBLE);
        }
        holder.btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuongSP=Integer.parseInt(holder.btnValue.getText().toString());
                int soLuongSPMoi=soLuongSP-1;
                holder.btnValue.setText(String.valueOf(soLuongSPMoi));
                gioHang.setSoLuongSP(soLuongSPMoi);
                if(soLuongSPMoi<=1){
                    holder.btnSubtract.setVisibility(View.INVISIBLE);
                    holder.btnAdd.setVisibility(View.VISIBLE);
                }
                else {
                    holder.btnSubtract.setText(View.VISIBLE);
                    holder.btnAdd.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuongSP=Integer.parseInt(holder.btnValue.getText().toString());
                int soLuongSPMoi=soLuongSP+1;
                holder.btnValue.setText(String.valueOf(soLuongSPMoi));
                gioHang.setSoLuongSP(soLuongSPMoi);
                if(soLuongSPMoi>=20){
                    holder.btnSubtract.setVisibility(View.VISIBLE);
                    holder.btnAdd.setVisibility(View.INVISIBLE);
                }
                else {
                    holder.btnSubtract.setText(View.VISIBLE);
                    holder.btnAdd.setVisibility(View.VISIBLE);
                }
            }
        });
        //chỗ này sai, check vẫn false
        gioHang.setCheckChonMua(holder.chkGH.isChecked());
        Toast.makeText(context, "SP "+gioHang.getTenSP()+" "+holder.chkGH.isChecked(), Toast.LENGTH_SHORT).show();
        return view;
    }
}

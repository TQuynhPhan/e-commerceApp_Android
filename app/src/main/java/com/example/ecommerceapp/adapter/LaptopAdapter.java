package com.example.ecommerceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.activity.ChiTietSanPhamActivity;
import com.example.ecommerceapp.activity.LaptopActivity;
import com.example.ecommerceapp.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.ViewHolder> {

    Context context;
    ArrayList<SanPham>arr;

    public LaptopAdapter(Context context, ArrayList<SanPham> arr) {
        this.context = context;
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview_laptop, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham=arr.get(position);
        holder.txtTen.setText(sanPham.getTen());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtGia.setText(decimalFormat.format(sanPham.getGia())+" đ");
        holder.txtMoTa.setMaxLines(2);
        holder.txtMoTa.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtMoTa.setText(sanPham.getMoTa());
        Picasso.get().load(sanPham.getHinhAnh())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_error)
                .into(holder.imgLT);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen, txtGia, txtMoTa;
        ImageView imgLT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen=itemView.findViewById(R.id.txtTenLT);
            txtGia=itemView.findViewById(R.id.txtGiaLT);
            txtMoTa=itemView.findViewById(R.id.txtMoTaLT);
            imgLT=itemView.findViewById(R.id.imgLT);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, ChiTietSanPhamActivity.class);
                    intent.putExtra("chiTietSP", arr.get(getPosition())); //hàm getPosition được hỗ trợ sẵn
                    context.startActivity(intent);
                }
            });
        }
    }
}

package com.example.ecommerceapp.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.ecommerceapp.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    Context context;
    ArrayList<SanPham> arr;

    public SanPhamAdapter(Context context, ArrayList<SanPham> arr) {
        this.context = context;
        this.arr = arr;
    }

    @NonNull
    @Override
    public SanPhamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview_spmoinhat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.ViewHolder holder, int position) {
        SanPham sanPham = arr.get(position);
        holder.txtTen.setText(sanPham.getTen());
        //holder.txtGia.setText(String.valueOf(sanPham.getGia()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");//định dạng có 1 dấu phẩy sau 3 chữ số trong giá sp
        holder.txtGia.setText(decimalFormat.format(sanPham.getGia())+" đ");
        Picasso.get().load(sanPham.getHinhAnh())
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_error)
                .into(holder.imgSP);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSP;
        TextView txtTen, txtGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSP = itemView.findViewById(R.id.imgSP);
            txtTen = itemView.findViewById(R.id.txtTenSP);
            txtGia = itemView.findViewById(R.id.txtGiaSP);

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

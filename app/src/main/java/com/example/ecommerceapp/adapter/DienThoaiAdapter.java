package com.example.ecommerceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class DienThoaiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<SanPham>arr;
    int VIEW_TYPE_GET_DATA=0;
    int VIEW_TYPE_LOADING=1;

    public DienThoaiAdapter(Context context, ArrayList<SanPham> arr) {
        this.context = context;
        this.arr = arr;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==VIEW_TYPE_GET_DATA) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview_dienthoai, null);
        }
        else{
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_bar_load, null);
        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            MyViewHolder myHolder= (MyViewHolder) holder;
            SanPham sanPham=arr.get(position);
            myHolder.txtTen.setText(sanPham.getTen());
            DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
            myHolder.txtGia.setText(decimalFormat.format(sanPham.getGia())+" đ");
            myHolder.txtMoTa.setMaxLines(2);
            myHolder.txtMoTa.setEllipsize(TextUtils.TruncateAt.END);
            myHolder.txtMoTa.setText(sanPham.getMoTa());
            Picasso.get().load(sanPham.getHinhAnh())
                    .placeholder(R.drawable.ic_no_image)
                    .error(R.drawable.ic_error)
                    .into(myHolder.imgDT);
        }
        else{
            LoadingViewHolder loadingHolder= (LoadingViewHolder) holder;
            loadingHolder.progressBarLoad.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position){
        return arr.get(position)==null?VIEW_TYPE_LOADING:VIEW_TYPE_GET_DATA;
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen, txtGia, txtMoTa;
        ImageView imgDT;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen=itemView.findViewById(R.id.txtTenDT);
            txtGia=itemView.findViewById(R.id.txtGiaDT);
            txtMoTa=itemView.findViewById(R.id.txtMoTaDT);
            imgDT=itemView.findViewById(R.id.imgDT);

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

    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBarLoad;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBarLoad=itemView.findViewById(R.id.progressBarLoad);
        }
    }
}

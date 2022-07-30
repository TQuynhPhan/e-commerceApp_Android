package com.example.ecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.model.GioHang;
import com.example.ecommerceapp.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarChiTietSP;
    ImageView imgChiTietSP;
    TextView txtTenChiTietSP, txtGiaChiTietSP, txtMoTaChiTietSP;
    Button btnThemGioHang;
    int id, gia;
    String ten, hinhAnh, moTa;
    int soLuong=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        toolbarChiTietSP=findViewById(R.id.toolBarChiTietSP);
        imgChiTietSP=findViewById(R.id.imgChiTietSP);
        txtTenChiTietSP=findViewById(R.id.txtTenChiTietSP);
        txtGiaChiTietSP=findViewById(R.id.txtGiaChiTietSP);
        txtMoTaChiTietSP=findViewById(R.id.txtMoTaChiTietSP);
        btnThemGioHang=findViewById(R.id.btnThemGioHang);

        btnThemGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean existSP=false;
                if(MainActivity.arrGioHang.size()>0){
                    for(int i=0;i<MainActivity.arrGioHang.size();i++) {
                        if (MainActivity.arrGioHang.get(i).getIdSP()==id){ //đã có sp vừa chọn trong giỏ hàng
                            MainActivity.arrGioHang.get(i).setSoLuongSP(++soLuong);
                            existSP=true;
                        }
                    }
                    if(existSP==false){//chưa có sản phẩm trong giỏ hàng
                        MainActivity.arrGioHang.add(new GioHang(id, ten, gia, hinhAnh, soLuong, false));
                    }
                }
                else{//giỏ hàng trống
                    MainActivity.arrGioHang.add(new GioHang(id, ten, gia, hinhAnh, soLuong, false));
                }
                startActivity(new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class));
            }
        });

        actionToolBar();
        getChiTietSP();
    }


    private void actionToolBar(){
        setSupportActionBar(toolbarChiTietSP);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTietSP.setNavigationOnClickListener(view -> finish());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuGH)
            startActivity(new Intent(this, GioHangActivity.class));
        return super.onOptionsItemSelected(item);
    }

    private void getChiTietSP(){
        Intent intent=getIntent();
        SanPham sanPham= (SanPham) intent.getSerializableExtra("chiTietSP");
        id=sanPham.getId();
        ten=sanPham.getTen();
        gia=sanPham.getGia();
        hinhAnh=sanPham.getHinhAnh();
        moTa=sanPham.getMoTa();
        txtTenChiTietSP.setText(ten);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtGiaChiTietSP.setText(decimalFormat.format(gia)+" đ");
        txtMoTaChiTietSP.setText(moTa);

        Picasso.get().load(hinhAnh)
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_error)
                .into(imgChiTietSP);
    }
}
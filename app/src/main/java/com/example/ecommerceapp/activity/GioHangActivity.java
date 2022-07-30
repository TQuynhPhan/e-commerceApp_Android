package com.example.ecommerceapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.GioHangAdapter;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {

    Toolbar toolBarGH;
    TextView txtTongTien, txtGHTrong;
    Button btnMuaNgay, btnMuaHang;
    ListView listViewGH;
    GioHangAdapter gioHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        toolBarGH=findViewById(R.id.toolBarGH);
        txtTongTien=findViewById(R.id.txtTongTien);
        txtGHTrong=findViewById(R.id.txtGHTrong);
        btnMuaNgay=findViewById(R.id.btnMuaNgay);
        btnMuaHang=findViewById(R.id.btnMuaHang);
        listViewGH=findViewById(R.id.listViewGH);
        gioHangAdapter=new GioHangAdapter(this, MainActivity.arrGioHang);
        listViewGH.setAdapter(gioHangAdapter);

        btnMuaNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GioHangActivity.this, MainActivity.class));
            }
        });

        actionToolBar();
        checkGioHang();
        tinhTongTien();
    }

    private void actionToolBar(){
        setSupportActionBar(toolBarGH);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBarGH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void checkGioHang(){
        if(MainActivity.arrGioHang.size()>0){
            listViewGH.setVisibility(View.VISIBLE);
            txtGHTrong.setVisibility(View.INVISIBLE);
            btnMuaNgay.setVisibility(View.INVISIBLE);
        }
        else{
            listViewGH.setVisibility(View.INVISIBLE);
            txtGHTrong.setVisibility(View.VISIBLE);
            btnMuaNgay.setVisibility(View.VISIBLE);
        }
        gioHangAdapter.notifyDataSetChanged(); //thay đổi visibility của giỏ hàng khi xóa sản phẩm trong giỏ hàng
    }

    private void tinhTongTien(){
        int tongTien=0;
        for(int i=0;i<MainActivity.arrGioHang.size();i++) {
            if (MainActivity.arrGioHang.get(i).isCheckChonMua()) {
                tongTien += MainActivity.arrGioHang.get(i).getGiaSP() * MainActivity.arrGioHang.get(i).getSoLuongSP();
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                txtTongTien.setText("Tổng tiền: "+decimalFormat.format(tongTien) + " đ");
            }
        }
    }
}
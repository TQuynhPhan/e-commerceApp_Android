package com.example.ecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.LaptopAdapter;
import com.example.ecommerceapp.model.SanPham;
import com.example.ecommerceapp.utils.CheckConnection;
import com.example.ecommerceapp.utils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaptopActivity extends AppCompatActivity {

    Toolbar toolbarLT;
    RecyclerView recyclerViewLT;
    LaptopAdapter laptopAdapter;
    ArrayList<SanPham> arrLT;
    View progressBarLoad;

    int idLoaiSP=0;
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);

        toolbarLT = findViewById(R.id.toolBarLT);
        recyclerViewLT = findViewById(R.id.recyclerViewLT);
        arrLT = new ArrayList<>();
        laptopAdapter = new LaptopAdapter(this, arrLT);
        recyclerViewLT.setHasFixedSize(true);
        recyclerViewLT.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewLT.setAdapter(laptopAdapter);

        LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        progressBarLoad=inflater.inflate(R.layout.progress_bar_load, null);


        if(CheckConnection.haveNetworkConnection(this)) {
            getIdLoaiSP();
            actionToolBar();
            getData(page);
        }
        else{
            Toast.makeText(this, "Vui lòng kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void getIdLoaiSP() {
        Intent intent = getIntent();
        idLoaiSP = intent.getIntExtra("idLoaiSP", -1);
    }

    private void actionToolBar() {
        setSupportActionBar(toolbarLT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarLT.setNavigationOnClickListener(new View.OnClickListener() { //với nút quay lại thì toolbar tự mặc định, k cần set icon
            @Override
            public void onClick(View view) {
                finish();
            } //quay về màn hình trước đó
        });
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

    private void getData(int pageLT) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url=Server.urlDT+String.valueOf(pageLT);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                int id=jsonObject.getInt("id");
                                String ten=jsonObject.getString("ten");
                                int gia=jsonObject.getInt("gia");
                                String hinhAnh=jsonObject.getString("hinhAnh");
                                String moTa=jsonObject.getString("moTa");
                                int idLoaiSP=jsonObject.getInt("idLoaiSP");
                                arrLT.add(new SanPham(id, ten, gia, hinhAnh, moTa, idLoaiSP));
                                laptopAdapter.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LaptopActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idLoaiSP", String.valueOf(idLoaiSP));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void LoadMoreData(){
        recyclerViewLT.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
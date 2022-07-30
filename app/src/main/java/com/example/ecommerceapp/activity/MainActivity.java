package com.example.ecommerceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapter.LoaiSPAdapter;
import com.example.ecommerceapp.adapter.SanPhamAdapter;
import com.example.ecommerceapp.adapter.SliderAdapter;
import com.example.ecommerceapp.model.GioHang;
import com.example.ecommerceapp.model.LoaiSP;
import com.example.ecommerceapp.model.SanPham;
import com.example.ecommerceapp.utils.CheckConnection;
import com.example.ecommerceapp.utils.Server;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    //ViewFlipper viewFlipper;
    SliderView sliderView;
    //ArrayList<String>arrImageSlider;
    SliderAdapter sliderAdapter;
    RecyclerView recyclerView;
    ListView listView;
    NavigationView navigationView;

    int[] arrImageSlider={
            R.drawable.animal1,
            R.drawable.animal2,
            R.drawable.animal3
    };

    ArrayList<LoaiSP>arrLoaiSP;
    LoaiSPAdapter loaiSPAdapter;

    ArrayList<SanPham>arrSpMoiNhat;
    SanPhamAdapter spMoiNhatAdapter;

    public static ArrayList<GioHang>arrGioHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawerLayout);
        toolbar=findViewById(R.id.toolBar);
        //viewFlipper=findViewById(R.id.viewFlipper);
        sliderView=findViewById(R.id.sliderView);
        recyclerView=findViewById(R.id.recyclerView);
        listView=findViewById(R.id.listView);
        navigationView=findViewById(R.id.navigationView);

        //arrImageSlider=new ArrayList<>();
        //arrImageSlider.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSl05wZMEsm_lRo3haN6O9OElB5mOeVJFHUUQ&usqp=CAU");
        //arrImageSlider.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoVKoziDNHMigaVWfjHJfWgqHsa9aWESZS-g&usqp=CAU");
        sliderAdapter=new SliderAdapter(arrImageSlider);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        arrLoaiSP=new ArrayList<>();
        loaiSPAdapter=new LoaiSPAdapter(this, arrLoaiSP);
        listView.setAdapter(loaiSPAdapter);

        arrSpMoiNhat=new ArrayList<>();
        spMoiNhatAdapter=new SanPhamAdapter(this, arrSpMoiNhat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(spMoiNhatAdapter);

        if(arrGioHang!=null){

        }
        else{
            arrGioHang=new ArrayList<>();
        }

        if(CheckConnection.haveNetworkConnection(this)) {
            ActionToolBar();
            getDataLoaiSP();
            getDataSpMoiNhat();
            clickItemListView();
        }
        else{
            Toast.makeText(this, "Vui lòng kiểm tra lại kết nối mạng", Toast.LENGTH_SHORT).show();
            finish();
        }

        //ActionViewFlipper();
    }

    private void ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
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

    private void getDataLoaiSP(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, Server.urlLoaiSP, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrLoaiSP.clear();
                        arrLoaiSP.add(0, new LoaiSP(0,"Trang chủ"));
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);
                                int id=jsonObject.getInt("id");
                                String ten=jsonObject.getString("ten");
                                arrLoaiSP.add(new LoaiSP(id, ten));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        arrLoaiSP.add(3, new LoaiSP(0, "Cài đặt"));
                        arrLoaiSP.add(4, new LoaiSP(0, "Thông tin"));
                        arrLoaiSP.add(5, new LoaiSP(0, "Đăng xuất"));
                        loaiSPAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void getDataSpMoiNhat(){
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, Server.urlSpMoiNhat, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrSpMoiNhat.clear();
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);
                                int id=jsonObject.getInt("id");
                                String ten=jsonObject.getString("ten");
                                int gia=jsonObject.getInt("gia");
                                String hinhAnh=jsonObject.getString("hinhAnh");
                                String moTa=jsonObject.getString("moTa");
                                int idLoaiSP=jsonObject.getInt("idLoaiSP");
                                arrSpMoiNhat.add(new SanPham(id, ten, gia, hinhAnh, moTa, idLoaiSP));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        spMoiNhatAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void clickItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i){
                    case 0:
                        intent=new Intent(MainActivity.this, MainActivity.class);
                        drawerLayout.closeDrawer(GravityCompat.START); //đóng lại drawerlayout sau khi nhấn vào item trên drawer
                        startActivity(intent);
                        break;
                    case 1:
                        intent=new Intent(MainActivity.this, DienThoaiActivity.class);
                        intent.putExtra("idLoaiSP", arrLoaiSP.get(i).getId());
                        drawerLayout.closeDrawer(GravityCompat.START); //đóng lại drawerlayout sau khi nhấn vào item trên drawer
                        startActivity(intent);
                        break;
                    case 2:
                        intent=new Intent(MainActivity.this, LaptopActivity.class);
                        intent.putExtra("idLoaiSP", arrLoaiSP.get(i).getId());
                        drawerLayout.closeDrawer(GravityCompat.START); //đóng lại drawerlayout sau khi nhấn vào item trên drawer
                        startActivity(intent);
                        break;
                    case 4:
                        intent=new Intent(MainActivity.this, ThongTinActivity.class);
                        drawerLayout.closeDrawer(GravityCompat.START); //đóng lại drawerlayout sau khi nhấn vào item trên drawer
                        startActivity(intent);
                        break;
                }
            }
        });
    }

//    private void ActionViewFlipper(){
//        ArrayList<String>arr=new ArrayList<>();
//        //arr.add("data:image/jpeg;base64,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");
//        //arr.add("data:image/jpeg;base64,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");
//        arr.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSl05wZMEsm_lRo3haN6O9OElB5mOeVJFHUUQ&usqp=CAU");
//        arr.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQoVKoziDNHMigaVWfjHJfWgqHsa9aWESZS-g&usqp=CAU");
//
//        for(int i=0;i<arr.size();i++){
//            ImageView imageView=new ImageView(getApplicationContext()); //vì viewflipper chỉ nhận imageview
//            Picasso.get().load(arr.get(i)).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//chỉnh kích thước imageview
//            viewFlipper.addView(imageView);
//        }
//        viewFlipper.setFlipInterval(5000); //1 imageview chạy trong 5s
//        viewFlipper.setAutoStart(true);
//        Animation slider_in= AnimationUtils.loadAnimation(this, R.anim.slider_in);
//        Animation slider_out=AnimationUtils.loadAnimation(this, R.anim.slider_out);
//        viewFlipper.setInAnimation(slider_in);
//        viewFlipper.setOutAnimation(slider_out);
//    }
}
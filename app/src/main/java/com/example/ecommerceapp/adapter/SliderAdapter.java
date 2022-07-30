package com.example.ecommerceapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ecommerceapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

//    ArrayList<String>arr;
//
//    public SliderAdapter(ArrayList<String> arr) {
//        this.arr = arr;
//    }
    int[]arr;

    public SliderAdapter(int[]arr){
        this.arr=arr;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        viewHolder.imageSlider.setImageResource(arr[position]);
    }

    @Override
    public int getCount() {
        return arr.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
        ImageView imageSlider;
        public Holder(View view){
            super(view);
            imageSlider=view.findViewById(R.id.imageSlider);
        }
    }
}

package com.app.foodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.foodorder.Activity.Domain.FoodDomain;
import com.app.foodorder.Activity.Helper.ManagementCart;
import com.app.foodorder.Activity.R;
import com.bumptech.glide.Glide;

import java.awt.font.TextAttribute;

public class DetailActivity extends AppCompatActivity {
private TextView btnaddtoCart;
private TextView title, gia, mota, soluong;
private ImageView btnTang, btnGiam, picFood;
private FoodDomain object;
int SoLuongOrder = 1;
private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId  = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picFood);
        title.setText(object.getTitle());
        gia.setText("$"+object.getGia());
        mota.setText(object.getMota());
        soluong.setText(String.valueOf(SoLuongOrder));
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoLuongOrder= SoLuongOrder+1;
                soluong.setText(String.valueOf(SoLuongOrder));
            }
        });
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SoLuongOrder>1){
                    SoLuongOrder=SoLuongOrder-1;
                }
                soluong.setText(String.valueOf(SoLuongOrder));
            }
        });
        btnaddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setSoLuong(SoLuongOrder);
                managementCart.insertFood(object);
            }
        });
    }

    private void initView() {
        btnaddtoCart = findViewById(R.id.btnaddtoCart);
        title = findViewById(R.id.titleDetail);
        gia = findViewById(R.id.detailGia);
        mota = findViewById(R.id.mota);
        soluong = findViewById(R.id.SoLuong);
        btnTang = findViewById(R.id.btnTang);
        btnGiam = findViewById(R.id.btnGiam);
        picFood = findViewById(R.id.picFood);
    }
}
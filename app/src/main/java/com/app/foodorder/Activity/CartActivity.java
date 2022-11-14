package com.app.foodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.foodorder.Activity.Adaptor.CartAdaptor;
import com.app.foodorder.Activity.Helper.ManagementCart;
import com.app.foodorder.Activity.Interface.ChangeNumberItemsListener;
import com.app.foodorder.Activity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewList;
private ManagementCart managementCart;
TextView txtTopping, txtPhiShip, txtThue, txtTongTien, emtytxt;
Button btnDatMon;
private double thue;
private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        managementCart = new ManagementCart(this);
        initView();
        initList();
        CaculatedCart();
        bottomNavigation();

        btnDatMon = findViewById(R.id.btnDatMon);

        btnDatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CheckoutActivity.class));
            }
        });
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homebtn = findViewById(R.id.btnHome);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,CartActivity.class));
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });
    }
    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        txtTopping = findViewById(R.id.txtTopping);
        txtThue = findViewById(R.id.txtThue);
        txtPhiShip = findViewById(R.id.txtPhiShip);
        txtTongTien = findViewById(R.id.txtTongTien);
        emtytxt = findViewById(R.id.emtytxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList = findViewById(R.id.cartView);
    }
    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartAdaptor(managementCart.getlistCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                CaculatedCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getlistCart().isEmpty()){
            emtytxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emtytxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void CaculatedCart(){
        double percentTax = 0.05;
        double delivery = 10;

        thue = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalFee() + thue + delivery) * 100) / 100;
        double itemtotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        txtTopping.setText("$"+itemtotal);
        txtThue.setText("$"+thue);
        txtPhiShip.setText("$"+delivery);
        txtTongTien.setText("$"+total);
    }
}
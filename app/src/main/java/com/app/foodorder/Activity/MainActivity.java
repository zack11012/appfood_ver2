package com.app.foodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.foodorder.Activity.Adaptor.DanhSachAdaptor;
import com.app.foodorder.Activity.Adaptor.PhoBienAdaptor;
import com.app.foodorder.Activity.Domain.DanhSachDomain;
import com.app.foodorder.Activity.Domain.FoodDomain;
import com.app.foodorder.Activity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter, adapter2;
private RecyclerView recyclerViewDanhSach,recyclerViewPhoBien;
private FirebaseAuth fauth;
private FirebaseFirestore fstore;
TextView txtName,txtLogOut,txtEmail;
Button btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleDanhSach();
        recyclerViewPhoBien();
        bottomNavigation();
        Profile();

        fstore = FirebaseFirestore.getInstance();
        fauth = FirebaseAuth.getInstance();
        ShowProfile();

        txtLogOut = findViewById(R.id.txtLogOut);
        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fauth.signOut();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void Profile(){
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homebtn = findViewById(R.id.btnHome);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }

    private void ShowProfile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
           return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();

        if (name==null){
            txtName.setVisibility(View.GONE);
        }
        else {
            txtName.setVisibility(View.VISIBLE);
            txtName.setText(name);
        }
        txtName.setText(name);
        txtEmail.setText(email);

    }

    private void recycleDanhSach() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewDanhSach=findViewById(R.id.recyclerView);
        recyclerViewDanhSach.setLayoutManager(linearLayoutManager);

        ArrayList<DanhSachDomain> danhSach= new ArrayList<>();
        danhSach.add(new DanhSachDomain("Pizza","cat_1"));
        danhSach.add(new DanhSachDomain("Burger","cat_2"));
        danhSach.add(new DanhSachDomain("Hotdog","cat_3"));
        danhSach.add(new DanhSachDomain("Drink","cat_4"));
        danhSach.add(new DanhSachDomain("Donut","cat_5"));
        adapter = new DanhSachAdaptor(danhSach);
        recyclerViewDanhSach.setAdapter(adapter);
    }
    private void recyclerViewPhoBien(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPhoBien = findViewById(R.id.recyclerView2);
        recyclerViewPhoBien.setLayoutManager(linearLayoutManager);
        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Pepperoni pizza","pop_3","slices peperoni,mozzerella phô mai, tiêu đen, sốt pizza",5.99));
        foodlist.add(new FoodDomain("Burger Chicken","pop_2","bò, cà chua, phô mai, sốt burger",2.99));
        foodlist.add(new FoodDomain("Pizza chay","pizza","dầu oliu, dầu thực vật, cà chua bi, rau salad",4.99));

        adapter2 = new PhoBienAdaptor(foodlist);
        recyclerViewPhoBien.setAdapter(adapter2);
    }
}
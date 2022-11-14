package com.app.foodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.foodorder.Activity.R;

public class IntroActivity extends AppCompatActivity {
private ConstraintLayout btnOrderNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        btnOrderNow=findViewById(R.id.btnOrderNow);
        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,Login.class));
            }
        });
    }
}
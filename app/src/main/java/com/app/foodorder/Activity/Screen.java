package com.app.foodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.app.foodorder.Activity.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        },3500);
    }
}
package com.app.foodorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Phone;
    private EditText Address;
    private RadioButton RDCASH;
    private RadioButton RDATM;
    private RadioButton cash;
    private RadioButton atm;
    private TextView txtName;
    private TextView txtPhone;
    private TextView txtAddress;
    private TextView txtThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Name = (EditText) findViewById(R.id.isname);
        Phone = (EditText) findViewById(R.id.isphone);
        Address = (EditText) findViewById(R.id.isaddress);
        RDCASH = (RadioButton) findViewById(R.id.cash);
        RDATM = (RadioButton) findViewById(R.id.atm);
    }
    public void activity_notice_thanh_toan(View view){
        setContentView(R.layout.activity_notice_thanh_toan);
        txtName = (TextView)findViewById(R.id.txtname);
        txtName.setText(Name.getText());
        txtPhone = (TextView)findViewById(R.id.txtphone);
        txtPhone.setText(Phone.getText());
        txtAddress =(TextView)findViewById(R.id.txtaddress);
        txtAddress.setText(Address.getText());
        txtThanhToan = (TextView)findViewById(R.id.txtthanhToan);
        if (RDCASH.isChecked()){
            txtThanhToan.setText("Tiền Mặt");
        }
        if (RDATM.isChecked()){
            txtThanhToan.setText("Chuyển Khoản ATM");
        }
    }
}
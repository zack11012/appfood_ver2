package com.app.foodorder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.app.foodorder.Activity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText nEmail, nPassword;
    Button nbtnLogin;
    TextView nbtnCreate;
    ProgressBar progressBar;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nEmail = findViewById(R.id.Email);
        nPassword = findViewById(R.id.Passwd);
        progressBar = findViewById(R.id.progressBar);
        nbtnLogin = findViewById(R.id.btnLogin);
        nbtnCreate = findViewById(R.id.createtext);

        fauth = FirebaseAuth.getInstance();



        nbtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));

            }
        });

        nbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = nEmail.getText().toString().trim();
                String passwd = nPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    nEmail.setError("Chưa nhập Email");
                    return;
                }
                if (TextUtils.isEmpty(passwd)){
                    nPassword.setError("Chưa nhập mật khẩu");
                    return;
                }
                if (passwd.length() < 6){
                    nPassword.setError("Password cần trên 6 ký tự");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fauth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(Login.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}
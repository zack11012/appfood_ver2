package com.app.foodorder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.foodorder.Activity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText nFullName,nEmail,nPassword,nPhone;
    Button nbtnRegister;
    TextView nbtnLogin;
    ProgressBar progressBar;

    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nFullName = findViewById(R.id.fullname);
        nEmail = findViewById(R.id.email);
        nPassword = findViewById(R.id.passwd);
        nPhone = findViewById(R.id.phone);
        nbtnRegister = findViewById(R.id.btnRegister);
        nbtnLogin = findViewById(R.id.createtext);
        progressBar = findViewById(R.id.progressbar);
        fauth = FirebaseAuth.getInstance();
        fstore =FirebaseFirestore.getInstance();


        nbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        nbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = nEmail.getText().toString().trim();
                String passwd = nPassword.getText().toString().trim();
                final String fullname = nFullName.getText().toString();
                final String phone = nPhone.getText().toString();

                if (TextUtils.isEmpty(email)){
                    nEmail.setError("Yêu cầu nhập Email");
                    return;
                }
                if (TextUtils.isEmpty(passwd)){
                    nPassword.setError("Yêu cầu nhập password");
                    return;
                }
                if (passwd.length() < 6){
                    nPassword.setError("Password phải trên 6 ký tự");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fauth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser fuser = fauth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"Thất bại: Email chưa được gửi"+e.getMessage());
                                }
                            });
                            Toast.makeText(getApplicationContext(), "User đã được tạo", Toast.LENGTH_SHORT).show();
                            userID = fauth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("user").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fullname);
                            user.put("fEmail",email);
                            user.put("fPhone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"Thành công: thông tin user đã được tạo bởi "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG,"Thất bại: "+ e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(Register.this, "Lỗi"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}
package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_User_orAdmin extends AppCompatActivity {
Button UserSignIn,AdminSignIn;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_or_admin);
        UserSignIn=findViewById(R.id.btnUserSignIn);
        AdminSignIn=findViewById(R.id.btnAdminSignIn);
        progressDialog=new ProgressDialog(Login_User_orAdmin.this);
        progressDialog.setTitle("Signing In");


        AdminSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Login_User_orAdmin.this, Admin_Login.class);
                startActivity(intent);

            }
        });
        UserSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Intent intent=new Intent(Login_User_orAdmin.this,UserSignIn.class);
                startActivity(intent);
                progressDialog.dismiss();
            }
        });
    }
}
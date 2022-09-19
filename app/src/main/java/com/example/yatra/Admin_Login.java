package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_Login extends AppCompatActivity {
EditText et1,et2;
Button login;
String st1,st2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        et1=findViewById(R.id.etAdminUsername);
        et2=findViewById(R.id.etAdminPassword);
        login=findViewById(R.id.btnAdminLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="admin";
                String p="12345";

                st1=et1.getText().toString();
                st2=et2.getText().toString();
                if(st1.equals(s)&&st2.equals(p)){
                    Toast.makeText(Admin_Login.this, "Login Succesful", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Admin_Login.this, Admin_Dashboard.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Admin_Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
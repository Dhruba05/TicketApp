package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yatra.databinding.ActivityMyProfileUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyProfile_user extends AppCompatActivity {
    ActivityMyProfileUserBinding binding;
    DatabaseReference reference;
    EditText editText;
    TextView tv1,tv2;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMyProfileUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog=new ProgressDialog(MyProfile_user.this);
        progressDialog.setTitle("Fetching Details");
        progressDialog.setMessage("Details are:");


        Intent i=getIntent();
        String mauth=i.getStringExtra("key");
        //editText.setText(uid);
        if(mauth!=null && !mauth.isEmpty()){
           // Toast.makeText(this, "Auth "+ mauth, Toast.LENGTH_SHORT).show();
            readData(mauth);

        }
        else{
            Toast.makeText(this, "No records found", Toast.LENGTH_SHORT).show();
        }


    }

    private void readData(String mauth) {

        reference= FirebaseDatabase.getInstance().getReference("Users");
        progressDialog.show();

        reference.child(mauth).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){

                    if(task.getResult().exists()){

                      //  Toast.makeText(MyProfile_user.this, "User data found", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot= task.getResult();
                        String USERNAME=String.valueOf(dataSnapshot.child("userName").getValue());
                        String EMAIL=String.valueOf(dataSnapshot.child("mail").getValue());
                        //Toast.makeText(MyProfile_user.this, "username" +" "+ USERNAME, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(MyProfile_user.this, "email" + " "+EMAIL, Toast.LENGTH_SHORT).show();
                        //binding.tvuserName.setText(USERNAME);
                        binding.tvuserName.setText(USERNAME);
                        binding.tvmailId.setText(EMAIL);
                        progressDialog.dismiss();



                        //binding.tvmailId.setText(EMAIL);


                    }
                    else {
                        Toast.makeText(MyProfile_user.this, "User Doesn't exists", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    Toast.makeText(MyProfile_user.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addPackage_admin extends AppCompatActivity {
    EditText packageName,source,destination,fare;
    Button save,show;
    String st1,st2,st3,st4;
    DatabaseReference dr;
    FirebaseDatabase fdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_package_admin);
        packageName=findViewById(R.id.et1);
        source=findViewById(R.id.et2);
        destination=findViewById(R.id.et3);
        fare=findViewById(R.id.et4);
        save=findViewById(R.id.btn1);
        show=findViewById(R.id.btn2);
        fdb=FirebaseDatabase.getInstance();
        dr=fdb.getReference("packages");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st1=packageName.getText().toString();
                st2=source.getText().toString();
                st3=destination.getText().toString();
                st4=fare.getText().toString();
                createPackages(st1,st2,st3,st4);


            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(addPackage_admin.this,package_details.class);
                startActivity(intent);
            }
        });

    }
    private void createPackages(String p_name, String p_from, String p_to, String p_fare){
        dr.child(p_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    if(snapshot.getValue()!=null){
                        Toast.makeText(addPackage_admin.this, "Package exists", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        dr.child(p_name).setValue(new Packages(p_name,p_from,p_to,p_fare));
                        Toast.makeText(addPackage_admin.this, "Inserted succesfully", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(addPackage_admin.this, "Server Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
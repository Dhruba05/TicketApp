package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class update_del_packages extends AppCompatActivity {
    EditText etFrom,etTo,etFare;
    TextView etRouteNumber;
    Button btnUpdate,btnDelete;
    DatabaseReference dr;
    FirebaseDatabase fdb;
    String num,frm,to,bfare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_del_packages);
        etRouteNumber=findViewById(R.id.etRouteNumber);
        etFrom=findViewById(R.id.etFrom);
        etTo=findViewById(R.id.etTo);
        etFare=findViewById(R.id.etFare);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        fdb=FirebaseDatabase.getInstance();
        dr= fdb.getReference("packages");
        num=getIntent().getExtras().getString("num");
        etRouteNumber.setText(num);
        frm=getIntent().getExtras().getString("frm");
        etFrom.setText(frm);
        to=getIntent().getExtras().getString("to");
        etTo.setText(to);
        bfare=getIntent().getExtras().getString("bfare");
        etFare.setText(bfare);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedFrom=etFrom.getText().toString();
                String updatedTo=etTo.getText().toString();
                String updatedFare=etFare.getText().toString();
                updateData(updatedFrom,updatedTo,updatedFare);

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();

            }
        });

    }
    private void updateData(String from,String to,String fare){
        dr.child(num).setValue(new Packages(num,from,to,fare));
        Toast.makeText(this, "Updated Succesfully", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(update_del_packages.this,addPackage_admin.class);
        startActivity(intent);

    }
    private void deleteData(){
        dr.child(num).removeValue();
        Toast.makeText(this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(update_del_packages.this,addPackage_admin.class);
        startActivity(intent);

    }
}
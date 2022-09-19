package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class package_details extends AppCompatActivity {
    ListView lv;
    DatabaseReference dr;
    FirebaseDatabase fdb;
    ArrayList<Packages> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);
        fdb=FirebaseDatabase.getInstance();
        dr=fdb.getReference("packages");
        lv=findViewById(R.id.lv);
        showData();

    }

    private void showData() {
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    Packages pck=ds.getValue(Packages.class);
                    arrayList.add(pck);
                }
                ABC obj=new ABC();
                lv.setAdapter(obj);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(package_details.this,update_del_packages.class);
                        intent.putExtra("num",arrayList.get(i).getP_name());
                        intent.putExtra("frm",arrayList.get(i).getP_from());
                        intent.putExtra("to",arrayList.get(i).getP_to());
                        intent.putExtra("bfare",arrayList.get(i).getP_fare());
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(package_details.this, "SERVER ERROR", Toast.LENGTH_SHORT).show();

            }
        });

    }
    class ABC extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater obj=getLayoutInflater();
            View row=obj.inflate(R.layout.packages_child,null);
            TextView tvPackageName=row.findViewById(R.id.textViewChild);
            TextView tvFrom=row.findViewById(R.id.textViewFrom);
            TextView tvTo=row.findViewById(R.id.textViewTo);
            TextView tvFare=row.findViewById(R.id.textViewbaseFare);
            tvPackageName.setText(arrayList.get(i).getP_name());
            tvFrom.setText(arrayList.get(i).getP_from());
            tvTo.setText(arrayList.get(i).getP_to());
            tvFare.setText(arrayList.get(i).getP_fare());
            return  row;

        }
    }
}
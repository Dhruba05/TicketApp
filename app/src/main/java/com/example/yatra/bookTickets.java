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

public class bookTickets extends AppCompatActivity {
    ListView lv1;
    DatabaseReference dbr;
    FirebaseDatabase fb;
    ArrayList<Tickets> arr=new ArrayList<>();
    String selectedItem="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tickets);
        lv1=findViewById(R.id.lvList);
        fb=FirebaseDatabase.getInstance();
        dbr=fb.getReference("packages");
        showRoute();



    }

    private void showRoute() {
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arr.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Tickets tk =dataSnapshot.getValue(Tickets.class);
                    arr.add(tk);
                }

                XYZ obj=new XYZ();
                lv1.setAdapter(obj);

                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        Intent intent=new Intent(bookTickets.this,paymentPage.class);
                        intent.putExtra("route",arr.get(i).getP_name());
                        intent.putExtra("source",arr.get(i).getP_from());
                        intent.putExtra("destiny",arr.get(i).getP_to());
                        intent.putExtra("price",arr.get(i).getP_fare());
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    class XYZ extends BaseAdapter{

        @Override
        public int getCount() {
            return arr.size();
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
            View row=obj.inflate(R.layout.ticket_child,null);
            TextView route=row.findViewById(R.id.textViewA);
            TextView source=row.findViewById(R.id.textViewB);
            TextView destination=row.findViewById(R.id.textViewC);
            TextView cost=row.findViewById(R.id.textViewD);

            route.setText(arr.get(i).getP_name());
            source.setText(arr.get(i).getP_from());
            destination.setText(arr.get(i).getP_to());
            cost.setText(arr.get(i).getP_fare());



            return row;
        }
    }



}
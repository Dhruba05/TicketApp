package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Ticket_history extends AppCompatActivity {
    ListView lv;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ArrayList<bookedTickets> arrayList=new ArrayList<>();
   // Intent intent=getIntent();
    //String res=intent.getStringExtra("x");
    FirebaseAuth pauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_history);
        lv=findViewById(R.id.listview);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Booked_Tickets");
        pauth=FirebaseAuth.getInstance();
        showBookingHistory();

    }

    private void showBookingHistory() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    bookedTickets bt=ds.getValue(bookedTickets.class);
                    arrayList.add(bt);
                }

                BTH obj=new BTH();
                lv.setAdapter(obj);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    class BTH extends BaseAdapter{

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
            View row=obj.inflate(R.layout.ticket_history_child,null);

            TextView tvRouteName=row.findViewById(R.id.tvbhRoute);
            TextView tvFrom=row.findViewById(R.id.tvbhFrom);
            TextView tvTo=row.findViewById(R.id.tvbhTo);
            //TextView tvPeople=findViewById(R.id.tvbhpeople);
            //TextView tvAmount=findViewById(R.id.tvbhAmount);

            tvRouteName.setText(arrayList.get(i).getBh_name());
            tvFrom.setText(arrayList.get(i).getBh_source());
            tvTo.setText(arrayList.get(i).getBh_destination());
            //tvPeople.setText(res);
//            tvAmount.setText(arrayList.get(i).getBh_amount());
            return row;
        }
    }
}
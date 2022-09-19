package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.yatra.R;

public class Admin_Dashboard extends AppCompatActivity  {
ListView lv;
    String selectedItem="";
String[] arr ={"Add Package"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        lv=findViewById(R.id.lv);
        DhrubaAdapter obj=new DhrubaAdapter();
        lv.setAdapter(obj);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem=arr[i];
                if(selectedItem.equals("Add Package")){
                    Intent intent=new Intent(Admin_Dashboard.this,addPackage_admin.class);
                    startActivity(intent);
                }
//                else if(selectedItem.equals("View Details")){
//                    Intent intent=new Intent(Admin_Dashboard.this,bookingDetails_admin.class);
//                    startActivity(intent);
//                }
                else {
                    Toast.makeText(Admin_Dashboard.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    class  DhrubaAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arr.length;
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
            View row=obj.inflate(R.layout.admindashboard_child,null);
            TextView tv=row.findViewById(R.id.tvchild);
            tv.setText(arr[i]);
            return row;
        }
    }


}
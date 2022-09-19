package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yatra.databinding.ActivityUserDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;

public class User_dashboard extends AppCompatActivity {
ActivityUserDashboardBinding binding;
FirebaseAuth auth;
String array[]={"My Profile","Book Ticket","Ticket History"};
String selectedItem="";
ListView lvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        lvUser=binding.lvUser;
        dAdapter obj=new dAdapter();
        lvUser.setAdapter(obj);
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem=array[i];
                if(selectedItem.equals("My Profile")){
                    //Toast.makeText(User_dashboard.this, "My Profile", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(User_dashboard.this,MyProfile_user.class);
                    String mauth=auth.getUid();
                    intent.putExtra("key",mauth);
                    startActivity(intent);
                }
                else if(selectedItem.equals("Book Ticket")){
                   // Toast.makeText(User_dashboard.this, "Book tickets", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(User_dashboard.this,bookTickets.class);
                    Toast.makeText(User_dashboard.this, "Book ticket", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else if(selectedItem.equals("Ticket History")){
                    Intent intent=new Intent(User_dashboard.this,Ticket_history.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(User_dashboard.this, "Error 404", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    class  dAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return array.length;
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
            View row=obj.inflate(R.layout.userdashboard_child,null);
            TextView tv=row.findViewById(R.id.tvchild1);
            tv.setText(array[i]);
            return row;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.menu_help:
                String url = "https://support.google.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_logout:
                auth.signOut();
                Intent intent=new Intent(User_dashboard.this,UserSignIn.class);
                startActivity(intent);
                break;
                }
        return true;
    }
}
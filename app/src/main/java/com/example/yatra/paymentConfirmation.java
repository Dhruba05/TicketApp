package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class paymentConfirmation extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    Button cnfPaybtn;
    String str1,str2,str3,str4,str5,str6;
    int passengerNum=0,price;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth authKey;

    String strName,strSource,strDest,strPrice,strPassengerNumber,Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);
        authKey=FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Booked_Tickets");
        cnfPaybtn=findViewById(R.id.cnfPaybtn);

        tv1=findViewById(R.id.ConfticketRoute);
        tv2=findViewById(R.id.ConfticketFrom);
        tv3=findViewById(R.id.ConfticketTo);
        tv5=findViewById(R.id.ticketAmount);
        tv6=findViewById(R.id.tvNoofPassenger);

        strName=getIntent().getExtras().getString("a");
        strSource=getIntent().getExtras().getString("b");
        strDest=getIntent().getExtras().getString("c");
        strPrice=getIntent().getExtras().getString("d");
        strPassengerNumber=getIntent().getExtras().getString("e");



        price=Integer.parseInt(strPrice);

        passengerNum=Integer.parseInt(strPassengerNumber);



        Amount=String.valueOf(price * passengerNum);




        tv1.setText(strName);
        tv2.setText(strSource);
        tv3.setText(strDest);
        tv5.setText(Amount);
        tv6.setText(strPassengerNumber);





        cnfPaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str1=getIntent().getExtras().getString("a");
                str2=getIntent().getExtras().getString("b");
                str3=getIntent().getExtras().getString("c");
                str4=getIntent().getExtras().getString("d");
                str5=getIntent().getExtras().getString("e");
                str6=String.valueOf((Integer.parseInt(str4))*Integer.parseInt(str5));






                sendMsg();
                createTicket(str1,str2,str3,str4,str5,str6);
                Toast.makeText(paymentConfirmation.this, "Payment Successful", Toast.LENGTH_SHORT).show();


                Intent in=new Intent(paymentConfirmation.this,User_dashboard.class);
                startActivity(in);
            }
        });


    }
    private void createTicket(String bh_name,String bh_source,
                              String bh_destination,String bh_baseprice,String bh_passengerNumber,String bh_amount){

        databaseReference.child(bh_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(bh_name).setValue(new bookedTickets(bh_name,bh_source,bh_destination,
                        bh_baseprice,bh_passengerNumber,bh_amount));
                Toast.makeText(paymentConfirmation.this, "Filled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(paymentConfirmation.this, "Server Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void sendMsg(){
        Intent i=new Intent(paymentConfirmation.this,paymentConfirmation.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent PI=PendingIntent.getActivity(paymentConfirmation.this,0,i,PendingIntent.FLAG_ONE_SHOT);

        String channel_ID="channelId";
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,channel_ID);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Ticket Booked");
        builder.setContentText("Routed selected :"+strName+ " | "+"\n"+"Amount is Rs."+str6);

        builder.setAutoCancel(true);
        builder.setSound(sound);
        builder.setContentIntent(PI);
        NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notiHigh=NotificationManager.IMPORTANCE_HIGH;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel nc=new NotificationChannel(channel_ID,"ABC",notiHigh);
            nm.createNotificationChannel(nc);


        }
        nm.notify(0,builder.build());




    }
}
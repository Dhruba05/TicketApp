package com.example.yatra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class paymentPage extends AppCompatActivity {
    String RouteName,Source,Destination,Price,PassengerNumber;
    TextView tv1,tv2,tv3,tv4;
    EditText etPassengerNumber;
    Button proceedtopayBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);
        tv1=findViewById(R.id.ticketRoute);
        tv2=findViewById(R.id.ticketBoardingPoint);
        tv3=findViewById(R.id.ticketDestination);
        tv4=findViewById(R.id.ticketFare);
        etPassengerNumber=(EditText)findViewById(R.id.etPassengerNumber);
        proceedtopayBtn=findViewById(R.id.proceedtopayBtn);

        RouteName=getIntent().getExtras().getString("route");
        Source=getIntent().getExtras().getString("source");
        Destination=getIntent().getExtras().getString("destiny");
        Price=getIntent().getExtras().getString("price");
       PassengerNumber=etPassengerNumber.getText().toString();

        tv1.setText(RouteName);
        tv2.setText(Source);
        tv3.setText(Destination);
        tv4.setText(Price);




        proceedtopayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PassengerNumber=etPassengerNumber.getText().toString();
                Intent intent=new Intent(paymentPage.this,paymentConfirmation.class);

                intent.putExtra("a",RouteName);
                intent.putExtra("b",Source);
                intent.putExtra("c",Destination);
                intent.putExtra("d",Price);
                intent.putExtra("e",PassengerNumber);


                startActivity(intent);



            }
        });




    }
}
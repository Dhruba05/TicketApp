package com.example.yatra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yatra.databinding.ActivityUserSignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserSignUp extends AppCompatActivity {
ActivityUserSignUpBinding binding;
private FirebaseAuth auth;
FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Users user=new Users(binding.etUsername.getText().toString(),
                                    binding.etEmail.getText().toString(),binding.etPassword.getText().toString());
                            String id=task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);
                            Toast.makeText(UserSignUp.this, "User Created Succesfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UserSignUp.this,UserSignIn.class);
                            //intent.putExtra("id",id);
                            startActivity(intent);



                        }
                        else {
                            Toast.makeText(UserSignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        });
        binding.tvAlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserSignUp.this,UserSignIn.class);
                startActivity(intent);
            }
        });

    }
}
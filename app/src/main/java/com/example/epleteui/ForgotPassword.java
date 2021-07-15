package com.example.epleteui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final Button btn_forgotPassword = (Button) findViewById(R.id.btn_forgotPassword) ;
        mAuth = FirebaseAuth.getInstance();

        final EditText emailText = findViewById(R.id.editText_forgotPassword);




        btn_forgotPassword.setOnClickListener(v -> {
            String email = emailText.getText().toString().trim();
            sendPasswordInEmail(email);
        });

    }

    private void sendPasswordInEmail(String email){

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this, task->{
           if (task.isSuccessful()){
               Toast.makeText(ForgotPassword.this, "GOOD HAPPENS", Toast.LENGTH_SHORT).show();
           }
           else {
               Toast.makeText(ForgotPassword.this, "SHIT HAPPENS", Toast.LENGTH_SHORT).show();
           }
        });
    }
}
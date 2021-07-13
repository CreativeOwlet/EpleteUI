package com.example.epleteui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity{



    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();
        //Buttons
        final Button login = (Button) findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();

        final TextView forgotPassword = findViewById(R.id.textView_forgotPassword);
        final EditText email = findViewById(R.id.editText_email);
        final EditText password = findViewById(R.id.editText_password);



        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(this, ForgotPassword.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();
            signIn(emailText, passwordText);
        });
    }



    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(HomeActivity.this, email + password,
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser user) {
        if(user != null) {
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
        }
        else
            Toast.makeText(HomeActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
    }

}
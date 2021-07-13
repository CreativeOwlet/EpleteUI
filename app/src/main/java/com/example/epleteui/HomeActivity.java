package com.example.epleteui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView signup;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();
        signup = findViewById( R.id.txt_Signup );
        btn_login = findViewById( R.id.btnLogin );

       signup.setOnClickListener(this );
        btn_login.setOnClickListener(this );
    }

    @Override
    public void onClick(View v) {
        int id =v.getId() ;
        Intent intent = null;
        switch (id){
            case R.id.txt_Signup:
                intent = new Intent( HomeActivity.this, RegistrationSelection.class );
                startActivity( intent );
                break;
            case R.id.btnLogin:
                intent = new Intent( HomeActivity.this, RegistrationForm.class );
                startActivity( intent );
            default:
                finish();
        }

    }
}
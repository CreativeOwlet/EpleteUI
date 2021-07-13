package com.example.epleteui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationSelection extends AppCompatActivity {
    ImageView driverImg, passengerImg, operatorImg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.registration_page);


        driverImg = findViewById( R.id.driver_img );
        passengerImg= findViewById( R.id.passenger_img );
        operatorImg = findViewById( R.id.operator_img );

        driverImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( RegistrationSelection.this, RegistrationForm.class );
                startActivity( intent );
            }
        } );
        passengerImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This on progress",Toast.LENGTH_LONG).show();
            }
        } );
        operatorImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"This on progress",Toast.LENGTH_LONG).show();
            }
        } );

    }
}

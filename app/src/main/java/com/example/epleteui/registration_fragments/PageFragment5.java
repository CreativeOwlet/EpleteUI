package com.example.epleteui.registration_fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.epleteui.R;


import java.util.Calendar;



import com.example.epleteui.RegistrationForm;
import com.example.epleteui.registration_persons.Driver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.content.ContentValues.TAG;


public class PageFragment5 extends Fragment {


    private String mNumber, email,password,fName,mName,lName,address,gender,nbi,driverLicense,selfie,validId,birthdate;
    private String operatorId;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth  = FirebaseAuth.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater
                .inflate(  R.layout.registration_page5,container
                        ,false );

        Button btn_submit = view.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(v->{
            gettingBundle();
            Date _birthdate = new Date(birthdate);

            Driver driver = new Driver(email,fName,mName,lName,address,operatorId,driverLicense,nbi,gender,email, mNumber,_birthdate,selfie,validId);

            System.out.println(driver.toString() + "WTF " +email + password);
            try {
                createAccount(email,password);
                db.collection("drivers").document(UUID.randomUUID().toString())
                        .set(driver)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }catch (Exception e){
                Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                System.out.println(e.getMessage());
            }

        });

        return view;
    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "Authentication Success.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private void gettingBundle(){

        Bundle b = this.getArguments();
        fName = b.getString("first_name");
        mName = b.getString("middle_name");
        lName = b.getString("last_name");
        address = b.getString("address");
        mNumber = b.getString("mobile_number");
        email = b.getString("email");
        password = b.getString("password");
        birthdate = b.getString("birthdate");
        gender = b.getString("gender");
        driverLicense=b.getString("driver_license");
        nbi =b.getString("nbi");
        operatorId=b.getString("operator_id");
        selfie = b.getString("selfie");
        validId = b.getString("valid_id");

        Toast.makeText(getActivity(), email + password +" 5", Toast.LENGTH_SHORT).show();
    }


}

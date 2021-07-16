package com.example.epleteui.registration_fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.epleteui.R;



public class PageFragment2 extends Fragment {


    private String email,password;

    public PageFragment2() {
        //Empty Constructor This is needed
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater
                .inflate(  R.layout.registration_page2,container
                        ,false );


        //Buttons
        Button buttonNext = view.findViewById(R.id.btn_next1);

        //Fields
        EditText mFirstName = view.findViewById(R.id.editText_firstName);
        EditText mMiddleName = view.findViewById(R.id.editText_middleName);
        EditText mLastName = view.findViewById(R.id.editText_lastName);
        EditText mAddress = view.findViewById(R.id.editText_address);
        EditText mMobileNumber = view.findViewById(R.id.editText_mobileNumber);



        //Button Listener
        buttonNext.setOnClickListener(v -> {
//            Getting the email and password
            gettingBundle();

            String _firstName = editTextToString(mFirstName);
            String _middleName = editTextToString(mMiddleName);;
            String _lastName = editTextToString(mLastName);;
            String _address = editTextToString(mAddress);;
            String _mobileNumber = editTextToString(mMobileNumber);

            Bundle bundle = new Bundle();
            bundle.putString("first_name",_firstName);
            bundle.putString("middle_name",_middleName);
            bundle.putString("last_name",_lastName);
            bundle.putString("address",_address);
            bundle.putString("mobile_number",_mobileNumber);
            bundle.putString("email", email);
            bundle.putString("password", password);

            //Creating the bundle
            PageFragment3 fragment3 = new PageFragment3();
            fragment3.setArguments(bundle);

            replaceFragment(fragment3);

        });

        return view;
    }


    private void gettingBundle(){
        Bundle b = this.getArguments();
        email = b.getString("EMAIL");
        password = b.getString("PASSWORD");

        Toast.makeText(getActivity(), email + password, Toast.LENGTH_SHORT).show();
    }

    private void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainLayoutRegistration, someFragment);
        transaction.commit();
    }

    private String editTextToString(EditText et) {
        return et.getText().toString().trim();
    }

}

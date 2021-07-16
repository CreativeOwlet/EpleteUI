package com.example.epleteui.registration_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.epleteui.R;



public class PageFragment extends Fragment {

    private EditText mEmail, mPassword,mConfirmPassword;
    public PageFragment() {
        //Blank constructor
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater
                .inflate(  R.layout.registration_page1,container
                        ,false );

        //Buttons
        ImageButton btn_next = view.findViewById(R.id.btn_next);

        //Fields
        //EditText
         mEmail = view.findViewById(R.id.editText_registrationEmail);
         mPassword = view.findViewById(R.id.editText_registrationPassword);
         mConfirmPassword = view.findViewById(R.id.editText_registrationConfirmPassword);


        //Button next listener
        btn_next.setOnClickListener(v -> {
            String _password = mPassword.getText().toString().trim();
            String _confirmPassword = mConfirmPassword.getText().toString().trim();
            String _email = mEmail.getText().toString().trim();
            if(_password.equals(_confirmPassword)){
                Bundle bundle = new Bundle();
                bundle.putString("EMAIL", _email);
                bundle.putString("PASSWORD",_password);

                //The next Fragment
                PageFragment2 nextFragment = new PageFragment2();
                nextFragment.setArguments(bundle);

                //To the next fragment
                replaceFragment(nextFragment);
            }
        });

        return view;
    }




    private void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainLayoutRegistration, someFragment);
        transaction.commit();

    }
}

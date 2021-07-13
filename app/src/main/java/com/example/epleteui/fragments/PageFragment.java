package com.example.epleteui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.epleteui.R;
import com.example.epleteui.RegistrationForm;
import com.example.epleteui.RegistrationSelection;

public class PageFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater
                .inflate(  R.layout.registration_page1,container
                        ,false );

        return view;

    }
}

package com.example.epleteui.registration_fragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.epleteui.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PageFragment3 extends Fragment {
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private String email,password,fName,mName,lName,address;
    private String mNumber;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater
                .inflate(  R.layout.registration_page3,container
                        ,false );


        Button btn_next = view.findViewById(R.id.btn_next2);

        //Textview for the date
        mDisplayDate = (TextView) view.findViewById(R.id.textView_date);

        //Spinners
        Spinner sGender = view.findViewById(R.id.spinnerGender);
        Spinner sOperatorName = view.findViewById(R.id.spinner_operatorList);



        //Firebase Initialize
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference cf = db.collection("operators");

        //populating the spinner
        List<String> operators = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, operators){
            //Setting the first index to be disabled
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sOperatorName.setAdapter(adapter);

        cf.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                operators.add(0,"--Operator Name--");
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String operator = document.getString("name");
                    String id = document.getString("id");

                    String concat = id + "-" + operator;

                    operators.add(concat);
                }
                adapter.notifyDataSetChanged();
            }
        });

        //Fields
        EditText mDriverLicense = view.findViewById(R.id.editText_driverLicense);
        EditText mNbiNo = view.findViewById(R.id.editText_nbiId);

        //Button listener
        btn_next.setOnClickListener(v -> {

            String mGender = sGender.getSelectedItem().toString().trim();
            String mOperatorNo = sOperatorName.getSelectedItem().toString();

            String _driverLicense = editTextToString(mDriverLicense);
            String _nbiNo = editTextToString(mNbiNo);

            String[] operatorId = mOperatorNo.split("-");


            gettingBundle();


            Bundle bundle = new Bundle();
            bundle.putString("first_name",fName);
            bundle.putString("middle_name",mName);
            bundle.putString("last_name",lName);
            bundle.putString("address",address);
            bundle.putString("mobile_number",mNumber);
            bundle.putString("email", email);
            bundle.putString("password", password);
            bundle.putString("birthdate",mDisplayDate.getText().toString().trim());
            bundle.putString("gender",mGender);
            bundle.putString("driver_license",_driverLicense);
            bundle.putString("nbi",_nbiNo);
            bundle.putString("operator_id",operatorId[0]);

            //Creating the bundle
            PageFragment4 fragment4 = new PageFragment4();
            fragment4.setArguments(bundle);

            replaceFragment(fragment4);

            Toast.makeText(getActivity(), operatorId[0], Toast.LENGTH_SHORT).show();

        });

        //Calendar when triggered
        mDisplayDate.setOnClickListener( v-> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(getContext(),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,
                    year,month,day);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        //after choosing the date it will be output
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mDisplayDate.setText(dayOfMonth + "/" + month + "/" + year);

            }
        };

        return view;
    }



    private void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainLayoutRegistration, someFragment);
        transaction.commit();
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

        Toast.makeText(getActivity(), email + password +" 1", Toast.LENGTH_SHORT).show();
    }

    private String editTextToString(EditText et) {
        return et.getText().toString().trim();
    }



}

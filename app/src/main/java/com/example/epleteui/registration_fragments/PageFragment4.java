package com.example.epleteui.registration_fragments;


import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.epleteui.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class PageFragment4 extends Fragment {
    private Uri validID;
    private Uri selfiePhoto;

    private String mNumber,email,password,fName,mName,lName,address,gender,nbi,driverLicense,birthdate;
    private String operatorId;

    //To override the image in the content
    private ImageView mValidId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater
                .inflate(  R.layout.registration_page4,container
                        ,false );

        //Button
        Button btn_next = view.findViewById(R.id.btn_next3);

        btn_next.setOnClickListener(v->{
            gettingBundle();

            Bundle bundle = new Bundle();
            bundle.putString("first_name",fName);
            bundle.putString("middle_name",mName);
            bundle.putString("last_name",lName);
            bundle.putString("address",address);
            bundle.putString("mobile_number",mNumber);
            bundle.putString("email", email);
            bundle.putString("password", password);
            bundle.putString("gender",gender);
            bundle.putString("driver_license",driverLicense);
            bundle.putString("nbi",nbi);
            bundle.putString("operator_id",operatorId);
            bundle.putString("birthdate",birthdate);
            bundle.putString("selfie",selfiePhoto.toString());
            bundle.putString("valid_id",validID.toString());

            //Creating the bundle
            PageFragment5 fragment5 = new PageFragment5();
            fragment5.setArguments(bundle);

            replaceFragment(fragment5);
        });

        //Image View
        ImageView camera = view.findViewById(R.id.image_camera);

        camera.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dispatchTakePictureIntent();

            }
        });

        mValidId = view.findViewById(R.id.image_validID);

        mValidId.setOnClickListener(v -> {
            openFileChoose();
        });

        return view;
    }



    static final int REQUEST_IMAGE_CAPTURE = 1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.println("Error creating File");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                selfiePhoto = FileProvider.getUriForFile(getActivity(),
                        "com.example.epleteui.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, selfiePhoto);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                //to see the content
                System.out.println(selfiePhoto);
            }
        }
    }

    String currentPhotoPath;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private static final int PICK_IMAGE_REQUEST = 1;
    private void openFileChoose() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            validID = data.getData();
            mValidId.setImageURI(validID);
            System.out.println(validID);
        }
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

        Toast.makeText(getActivity(), email + password +" 3", Toast.LENGTH_SHORT).show();
    }

    private void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainLayoutRegistration, someFragment);
        transaction.commit();
    }

}

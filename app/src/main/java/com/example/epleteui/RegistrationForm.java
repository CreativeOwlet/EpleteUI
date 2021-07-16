package com.example.epleteui;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.epleteui.registration_fragments.PageFragment;
import com.example.epleteui.registration_fragments.PageFragment2;
import com.example.epleteui.registration_fragments.PageFragment3;
import com.example.epleteui.registration_fragments.PageFragment4;
import com.example.epleteui.registration_fragments.PageFragment5;


import java.util.ArrayList;
import java.util.List;

public class RegistrationForm extends AppCompatActivity {

    // View pager
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    //
    ImageView backImg_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.registration_form);

        //

        getSupportActionBar().hide();
        pager = findViewById( R.id.pager );

        //Pager View
        List<Fragment> list = new ArrayList<>();
        list.add( new PageFragment() );
        list.add( new PageFragment2() );
        list.add( new PageFragment3() );
        list.add( new PageFragment4() );
        list.add( new PageFragment5() );
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),list);



        //Adapter
        pager.setAdapter( pagerAdapter );


    }



}

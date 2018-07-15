package com.example.evgeniy.htcandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(UsersFragment.class.getSimpleName());
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.container, UsersFragment.newInstance(), UsersFragment.class.getSimpleName())
                    .commit();
        }
    }


}

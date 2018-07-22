package com.example.evgeniy.htcandroid;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int EXIT_TIMEOUT = 2000;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, MainFragment.newInstance(), MainFragment.class.getSimpleName())
                .disallowAddToBackStack()
                .commit();
    }

    @Override
    public void onBackPressed() {
        //
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(BlankFragment.class.getSimpleName()) == null) {
            if (exitTime == 0 || System.currentTimeMillis() - exitTime > EXIT_TIMEOUT) {
                Toast.makeText(this, R.string.tap_again_for_exit, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        } else {
            fragmentManager.popBackStack();
        }
    }
}

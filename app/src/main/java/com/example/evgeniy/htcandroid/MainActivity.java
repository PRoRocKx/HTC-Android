package com.example.evgeniy.htcandroid;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int TIMEOUT = 2000;
    private long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, MainFragment.newInstance(), MainFragment.class.getSimpleName())
                .disallowAddToBackStack()
                .commit();
    }

    @Override
    public void onBackPressed() {
        //
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(BlankFragment.class.getSimpleName()) == null) {
            if (time == 0 || System.currentTimeMillis() - time > TIMEOUT) {
                Toast.makeText(this, R.string.tap_again_for_exit, Toast.LENGTH_SHORT).show();
                time = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        } else {
            fragmentManager.popBackStack();
        }
    }
}

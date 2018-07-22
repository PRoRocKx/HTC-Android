package com.example.evgeniy.htcandroid;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


public class BlankFragment extends Fragment {

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Random random = new Random();
        view.setBackgroundColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        Button next = view.findViewById(R.id.btnNext2);
        next.setOnClickListener(v -> addFragment());
    }

    private void addFragment() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, BlankFragment.newInstance(), BlankFragment.class.getSimpleName())
                    .addToBackStack(BlankFragment.class.getSimpleName())
                    .commit();
        }
    }
}

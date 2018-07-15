package com.example.evgeniy.htcandroid;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button next = view.findViewById(R.id.btnNext);
        next.setOnClickListener(v -> addFragment());
    }

    private void addFragment() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, BlankFragment.newInstance(), BlankFragment.class.getSimpleName())
                    .addToBackStack(BlankFragment.class.getSimpleName())
                    .commit();
        }
    }


}

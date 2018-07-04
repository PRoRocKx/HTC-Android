package com.example.evgeniy.htcandroid;


import android.os.Bundle;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button next = view.findViewById(R.id.btnNext);
        next.setOnClickListener(v -> addFragment());
        return view;
    }

    private void addFragment() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, BlankFragment.newInstance(), "BlankFragment")
                    .addToBackStack("BlankFragment")
                    .commit();
        }
    }


}

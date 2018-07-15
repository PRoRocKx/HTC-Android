package com.example.evgeniy.htcandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgeniy.htcandroid.dummy.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class UsersFragment extends Fragment {

    private static final int REQUEST_ADD_USER = 1;
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String INFO = "info";
    private static final String USERS = "User";



    public static UsersFragment newInstance() {
        return new UsersFragment();
    }


    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Unbinder unbinder;


    private MyUsersRecyclerViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        if (savedInstanceState == null) {
            adapter = new MyUsersRecyclerViewAdapter(new ArrayList<>());
        } else {
            adapter = new MyUsersRecyclerViewAdapter(savedInstanceState.getParcelableArrayList(USERS));
        }
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(view1 -> showDialog());
    }

    private void showDialog() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        // Fragment prev = getFragmentManager().findFragmentByTag("addUserFragment");
        //if (prev != null) {
        //    fragmentTransaction.remove(prev);
        // }
        // fragmentTransaction.addToBackStack(null);

        AddUserFragment addUserFragment = AddUserFragment.newInstance();
        addUserFragment.setTargetFragment(this, REQUEST_ADD_USER);
        addUserFragment.show(fragmentTransaction, AddUserFragment.class.getSimpleName());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_USER) {
            if (resultCode == Activity.RESULT_OK) {
                String name = data.getStringExtra(NAME);
                String address = data.getStringExtra(ADDRESS);
                String info = data.getStringExtra(INFO);
                User user = new User(name, address, info);
                adapter.addUser(user);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USERS, (ArrayList<? extends Parcelable>) adapter.getValues());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (unbinder != null) {
            unbinder.unbind();
        }
        adapter = null;
    }


}

package com.example.evgeniy.htcandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgeniy.htcandroid.dummy.Users;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class UsersFragment extends Fragment {

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Unbinder unbinder;



    private static final int REQUEST_ADD_USER = 1;

    private MyUsersRecyclerViewAdapter adapter;

    @SuppressWarnings("unused")
    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (savedInstanceState ==null) {
            adapter = new MyUsersRecyclerViewAdapter(new ArrayList<>());
        }
        else
            adapter = new MyUsersRecyclerViewAdapter(savedInstanceState.getParcelableArrayList("Users"));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(view1 -> showDialog());
        return view;
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
        addUserFragment.show(fragmentTransaction, "addUserFragment");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_ADD_USER) {
                String name = data.getStringExtra("name");
                String address = data.getStringExtra("address");
                String info = data.getStringExtra("info");
                Users.User user = new Users.User(name, address, info);
                adapter.addUser(user);
                recyclerView.getAdapter().notifyItemInserted(adapter.getValues().lastIndexOf(user));
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Users", (ArrayList<? extends Parcelable>) adapter.getValues());
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

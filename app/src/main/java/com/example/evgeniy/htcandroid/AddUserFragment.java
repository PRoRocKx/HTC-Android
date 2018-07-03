package com.example.evgeniy.htcandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Evgen on 02.07.2018.
 */

public class AddUserFragment extends DialogFragment {

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.address)
    EditText address;

    @BindView(R.id.info)
    EditText info;

    private Unbinder unbinder;

    static AddUserFragment newInstance() {
        return new AddUserFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog, null);
        unbinder = ButterKnife.bind(this, view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Ok", (dialog, which) -> {
                    //отправляем результат обратно
                    Intent intent = new Intent();
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("address", address.getText().toString());
                    intent.putExtra("info", info.getText().toString());
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                });
        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}

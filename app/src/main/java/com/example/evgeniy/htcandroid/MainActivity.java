package com.example.evgeniy.htcandroid;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    static final String LIST = "list";

    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.btnPrev)
    Button btnPrev;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.stackText)
    TextView stackText;

    private Unbinder unbinder;

    private ArrayList<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        if (savedInstanceState != null) {
            list = savedInstanceState.getIntegerArrayList(LIST);
        } else {
            list = new ArrayList<>();
            list.add(0);
        }
        showCurrent();
        btnNext.setOnClickListener(view -> setNewInt());
        btnPrev.setOnClickListener(view -> setPrev());
    }

    private void setNewInt() {
        list.add(new Random().nextInt());
        showCurrent();
    }

    private void setPrev() {
        list.remove(list.size() - 1);
        showCurrent();
    }

    private void showCurrent() {
        textView.setText(String.valueOf(list.get(list.size() - 1)));
        if (list.size() > 1) {
            btnPrev.setVisibility(View.VISIBLE);
        } else {
            btnPrev.setVisibility(View.GONE);
        }
        showStack();
    }

    private void showStack() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i)).append('\n');
        }
        stackText.setText(stringBuilder.toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(LIST, list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}

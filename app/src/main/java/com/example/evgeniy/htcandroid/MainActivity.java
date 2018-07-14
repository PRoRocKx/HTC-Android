package com.example.evgeniy.htcandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    private Stack<Integer> stack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        stack = new Stack<>();
        if (savedInstanceState != null) {
            stack.addAll(savedInstanceState.getIntegerArrayList(LIST));
        } else {
            stack.push(0);
        }
        showCurrent();
        btnNext.setOnClickListener(view -> setNewInt());
        btnPrev.setOnClickListener(view -> setPrev());
    }

    private void setNewInt() {
        stack.push(new Random().nextInt());
        showCurrent();
    }

    private void setPrev() {
        stack.pop();
        showCurrent();
    }

    private void showCurrent() {
        textView.setText(String.valueOf(stack.peek()));
        if (stack.size() > 1) {
            btnPrev.setVisibility(View.VISIBLE);
        } else {
            btnPrev.setVisibility(View.GONE);
        }
        showStack();
    }

    private void showStack() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            stringBuilder.append(stack.get(i)).append('\n');
        }
        stackText.setText(stringBuilder.toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(LIST, new ArrayList<>(stack));
    }

}

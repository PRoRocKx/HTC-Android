package com.example.evgeniy.htcandroid;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.evgeniy.htcandroid.Converters.*;

public class MainActivity extends AppCompatActivity {

    @Nullable
    private Unbinder unbinder;

    @BindView(R.id.calcDP)
    TextView calcDP;
    @BindView(R.id.editDP)
    EditText editDP;

    @BindView(R.id.calcPX)
    TextView calcPX;
    @BindView(R.id.editPX)
    EditText editPX;

    @BindView(R.id.xdp)
    TextView xdp;
    @BindView(R.id.ydp)
    TextView ydp;

    @BindView(R.id.xpx)
    TextView xpx;
    @BindView(R.id.ypx)
    TextView ypx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        editDP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (text.isEmpty()){
                    return;
                }
                calcDP.setText(String.valueOf(pxFromDp(Float.parseFloat(text),getApplicationContext())));
            }
        });

        editPX.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (text.isEmpty()){
                    return;
                }
                calcPX.setText(String.valueOf(dpFromPx(Float.parseFloat(text),getApplicationContext())));
            }
        });
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        xdp.setText(String.valueOf(displayMetrics.xdpi));
        ydp.setText(String.valueOf(displayMetrics.ydpi));
        xpx.setText(String.valueOf(displayMetrics.widthPixels));
        ypx.setText(String.valueOf(displayMetrics.heightPixels));
    }



    @Override
    protected void onDestroy() {
        if (unbinder != null)
            unbinder.unbind();
        super.onDestroy();

    }
}

package com.brandcash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.brandcash.util.SharedPrefs;

/**
 * Created by savva.volobuev on 02.07.2017.
 */

public class EnterActivity extends AppCompatActivity {

    private TextView next;
    private EditText phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_enter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.enter_label);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        next = (TextView) findViewById(R.id.next);
        phone = (EditText) findViewById(R.id.phone);

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 10) {
                    unlock();
                } else {
                    lock();
                }
            }

        });
    }

    private void lock() {
        next.setTextColor(Color.parseColor("#989dab"));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
    }

    private void unlock() {
        next.setTextColor(Color.parseColor("#ffffff"));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefs.setPrefPhone(phone.getText().toString());
                startActivity(new Intent(EnterActivity.this, EnterSmsActivity.class).putExtra(EnterSmsActivity.EXTRA_PHONE, phone.getText().toString()));
            }
        });
    }
}

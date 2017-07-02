package com.brandcash;

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

import com.brandcash.model.User;
import com.brandcash.model.UserRegestrated;
import com.brandcash.serverapi.ServerClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 02.07.2017.
 */

public class RegActivity extends AppCompatActivity {
    private TextView next;
    private EditText phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_enter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.reg_label);
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
                User user = new User();
                user.setPhone(phone.getText().toString());
                Call<UserRegestrated> call = ServerClient.getServerApiService().regUser(user);
                call.enqueue(new Callback<UserRegestrated>() {
                    @Override
                    public void onResponse(Call<UserRegestrated> call, Response<UserRegestrated> response) {
                        if (response.body() != null && response.code() == 200) {
                            startActivity(new Intent(RegActivity.this, RegSmsActivity.class).putExtra(RegSmsActivity.EXTRA_PHONE_REG, phone.getText().toString()));
                        }
                    }

                    @Override
                    public void onFailure(Call<UserRegestrated> call, Throwable t) {
                        int i = 1;
                        i++;
                    }
                });
            }
        });
    }
}

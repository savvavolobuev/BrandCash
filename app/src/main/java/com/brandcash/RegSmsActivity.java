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
import android.widget.Toast;

import com.brandcash.model.Code;
import com.brandcash.model.PhoneNum;
import com.brandcash.model.Session;
import com.brandcash.serverapi.ServerClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 02.07.2017.
 */
public class RegSmsActivity extends AppCompatActivity {
    public static final String EXTRA_PHONE_REG = "EXTRA_PHONE_REG";
    private EditText codeSms;
    private TextView next;
    private String phone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_enter_sms);
        phone = getIntent().getStringExtra(EXTRA_PHONE_REG);
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
        codeSms = (EditText) findViewById(R.id.code_sms);
        next = (TextView) findViewById(R.id.next);

        codeSms.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 4) {
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
                PhoneNum phoneNum = new PhoneNum();
                phoneNum.setPhone(phone);
                Call<Code> call = ServerClient.getServerApiService().restorePhoneCode(phoneNum);
                call.enqueue(new Callback<Code>() {
                    @Override
                    public void onResponse(Call<Code> call, Response<Code> response) {
                        if (response.body() != null && response.code() == 200) {

                            Call<Session> callSession = ServerClient.getServerApiService().getSession(phone, response.body().getCode());
                            callSession.enqueue(new Callback<Session>() {
                                @Override
                                public void onResponse(Call<Session> call, Response<Session> response) {
                                    if (response.body() != null && response.code() == 200) {
                                        startActivity(new Intent(RegSmsActivity.this, MainActivity.class));
                                    }
                                }

                                @Override
                                public void onFailure(Call<Session> call, Throwable t) {
                                    Toast.makeText(RegSmsActivity.this, "Ошибка регистрации",Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                    }

                    @Override
                    public void onFailure(Call<Code> call, Throwable t) {
                        Toast.makeText(RegSmsActivity.this, "Ошибка регистрации",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

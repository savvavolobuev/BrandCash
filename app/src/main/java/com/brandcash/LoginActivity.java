package com.brandcash;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.brandcash.model.Session;
import com.brandcash.serverapi.ServerClient;
import com.brandcash.util.SharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 30.06.2017.
 */

public class LoginActivity extends Activity {

    private Button regButton;
    private Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_activity);

        regButton = (Button) findViewById(R.id.reg);
        enterButton = (Button) findViewById(R.id.enter);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
            }
        });
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, EnterActivity.class));
            }
        });
        if (!TextUtils.isEmpty(SharedPrefs.getPrefSid())) {
            Call<Session> callSession = ServerClient.getServerApiService().checkSession(SharedPrefs.getPrefSid());
            final MaterialDialog dialog = new MaterialDialog.Builder(LoginActivity.this).content("Пожайлуста, подождите").progress(true, 0).build();
            dialog.show();
            callSession.enqueue(new Callback<Session>() {
                @Override
                public void onResponse(Call<Session> call, Response<Session> response) {
                    dialog.dismiss();
                    if (response.body() != null && response.code() == 200) {
                        Session session = response.body();
                        SharedPrefs.setPrefSid(session.getSid());
                        SharedPrefs.setPrefExpires(session.getExpires());
                        SharedPrefs.setPrefUserId(session.getUserId());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<Session> call, Throwable t) {
                    dialog.dismiss();
                    //Toast.makeText(LoginActivity.this, "Ошибка авторизации", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

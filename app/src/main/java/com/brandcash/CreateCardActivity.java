package com.brandcash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.brandcash.model.CardData;
import com.brandcash.serverapi.ServerClient;
import com.brandcash.util.SharedPrefs;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 11.07.2017.
 */

public class CreateCardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int SCAN_REQUEST_CODE = 512;
    private NavigationView navigationView;
    private LinearLayout scan;
    private TextView done;
    private EditText cardNum;
    private EditText nameHolder;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_cash) {

        } else if (id == R.id.nav_offer) {
            TaskStackBuilder.create(this)
                    .addParentStack(MainActivity.class)
                    .addNextIntent(new Intent(this, CreateCardActivity.class))
                    .startActivities();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_create_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.new_card);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        done = (TextView) findViewById(R.id.done);
        cardNum = (EditText) findViewById(R.id.card_num);
        nameHolder = (EditText) findViewById(R.id.name_holder);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ((TextView) headerView.findViewById(R.id.nav_header_phone)).setText(SharedPrefs.getPrefPhone());
        navigationView.setNavigationItemSelectedListener(this);


        scan = (LinearLayout) findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanIntent = new Intent(CreateCardActivity.this, CardIOActivity.class);

                // customize these values to suit your needs.
                //scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
                //scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
                //scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true);
                //scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_RESULT, true);
                // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
                startActivityForResult(scanIntent, SCAN_REQUEST_CODE);
            }
        });

        nameHolder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ((s.toString().length() > 0) && (cardNum.getText().toString().length() > 12)) {
                    unlock();
                } else {
                    lock();
                }
            }
        });
        cardNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ((s.toString().length() > 12) && (!nameHolder.getText().toString().isEmpty())) {
                    unlock();
                } else {
                    lock();
                }
            }
        });
    }


    private void lock() {
        done.setTextColor(Color.parseColor("#989dab"));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });
    }

    private void unlock() {
        done.setTextColor(Color.parseColor("#ffffff"));
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardData card = new CardData();
                card.setNumber(cardNum.getText().toString());
                card.setFirstName(nameHolder.getText().toString());
                card.setLastName(nameHolder.getText().toString());
                Call<ResponseBody> call = ServerClient.getServerApiService().addCard(SharedPrefs.getPrefUserId(), SharedPrefs.getPrefSid(), card);
                final MaterialDialog dialog = new MaterialDialog.Builder(CreateCardActivity.this).content("Пожайлуста, подождите").progress(true, 0).title("Пожайлуста, подождите").build();
                dialog.show();
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        dialog.dismiss();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        dialog.dismiss();
                        Log.d("httpserver", "fail");
                    }
                });

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";

                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
            } else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
        }
        // else handle other activity results
    }
}

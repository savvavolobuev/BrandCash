package com.brandcash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.brandcash.model.Document;
import com.brandcash.model.ReceiptResponseData;
import com.brandcash.serverapi.ServerClient;
import com.brandcash.ui.PriceView;
import com.brandcash.ui.ReceiptItemRecyclerAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 09.06.2017.
 */

public class QrResultActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private TextView name;
    private TextView inn;
    private TextView number;
    private TextView shift;
    private TextView operator;
    private PriceView sum;
    private PriceView cashback;
    private TextView bonuses;
    private RecyclerView itemsRecycler;
    private ReceiptItemRecyclerAdapter adapter;
    private ReceiptResponseData responseData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_qr_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.offer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        name = (TextView) findViewById(R.id.user);
        inn = (TextView) findViewById(R.id.inn);
        number = (TextView) findViewById(R.id.itemNumber);
        shift = (TextView) findViewById(R.id.shiftNumber);
        operator = (TextView) findViewById(R.id.operator);
        sum = (PriceView) findViewById(R.id.sum);
        cashback = (PriceView) findViewById(R.id.cashback);
        bonuses = (TextView) findViewById(R.id.bonuses);
        itemsRecycler = (RecyclerView) findViewById(R.id.itemsRecycler);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Call<ReceiptResponseData> call = ServerClient.getServerApiService().add("1", "20170528T080900", "11.40", "8710000100593507", "8334", "4098962504", "TVRRMk9ERTROak13TVRrNAo=");
        call.enqueue(new Callback<ReceiptResponseData>() {
            @Override
            public void onResponse(Call<ReceiptResponseData> call, Response<ReceiptResponseData> response) {
                if (response.code() == 200) {
                    responseData = response.body();
                    if (responseData != null) {
                        if (responseData.isFound() == null) {

                        } else if (responseData.isFound()) {
                            adapter = new ReceiptItemRecyclerAdapter(responseData.getData().getItems());
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(QrResultActivity.this);
                            itemsRecycler.setLayoutManager(mLayoutManager);
                            itemsRecycler.setItemAnimator(new DefaultItemAnimator());
                            itemsRecycler.setAdapter(adapter);
                            name.setText(responseData.getData().getUser());
                            inn.setText(responseData.getData().getUserInn());
                            //number.setText("Чек № " + document.getReceiptData().getNds18());
                            shift.setText("Смена № " + responseData.getData().getShiftNumber());
                            operator.setText("Кассир " + responseData.getData().getOperator());
                            sum.setPrice(responseData.getData().getTotalSum());
                            cashback.setPrice(responseData.getData().getCashTotalSum());
                            bonuses.setText(responseData.getData().getEcashTotalSum() + " ");
                        } else {

                        }
                    }
                } else if (response.code() == 403) {

                }

            }

            @Override
            public void onFailure(Call<ReceiptResponseData> call, Throwable t) {
                int i = 1;
                i++;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_cash) {

        } else if (id == R.id.nav_offer) {
            TaskStackBuilder.create(this)
                    .addParentStack(MainActivity.class)
                    .addNextIntent(new Intent(this, OfferListActivity.class))
                    .startActivities();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}

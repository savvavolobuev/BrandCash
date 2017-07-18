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

import com.brandcash.model.ReceiptResponseData;
import com.brandcash.ui.PriceView;
import com.brandcash.ui.ReceiptItemRecyclerAdapter;
import com.brandcash.util.SharedPrefs;

/**
 * Created by savva.volobuev on 17.07.2017.
 */

public class ReceiptInfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String EXTRA_RECEIPT = "EXTRA_RECEIPT";
    private NavigationView navigationView;
    private ReceiptResponseData receipt;
    private PriceView sum;
    private PriceView cashback;
    private TextView bonuses;
    private ReceiptItemRecyclerAdapter adapter;
    private RecyclerView itemsRecycler;
    private TextView name;
    private TextView inn;
    private TextView number;
    private TextView shift;
    private TextView operator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_info_activity);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_qr_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.receipt);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ((TextView) headerView.findViewById(R.id.nav_header_phone)).setText(SharedPrefs.getPrefPhone());
        navigationView.setNavigationItemSelectedListener(this);
        receipt = (ReceiptResponseData) getIntent().getSerializableExtra(EXTRA_RECEIPT);
        sum = (PriceView) findViewById(R.id.sum);
        cashback = (PriceView) findViewById(R.id.cashback);
        bonuses = (TextView) findViewById(R.id.bonuses);
        itemsRecycler = (RecyclerView) findViewById(R.id.itemsRecycler);
        name = (TextView) findViewById(R.id.user);
        inn = (TextView) findViewById(R.id.inn);
        number = (TextView) findViewById(R.id.itemNumber);
        shift = (TextView) findViewById(R.id.shiftNumber);
        operator = (TextView) findViewById(R.id.operator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sum.setPrice(receipt.getData().getTotalSum());
        cashback.setPrice(receipt.getData().getCashTotalSum());
        bonuses.setText(receipt.getData().getEcashTotalSum() + " ");
        if (receipt.getFound()) {
            adapter = new ReceiptItemRecyclerAdapter(receipt.getData().getItems());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ReceiptInfoActivity.this);
            itemsRecycler.setLayoutManager(mLayoutManager);
            itemsRecycler.setItemAnimator(new DefaultItemAnimator());
            itemsRecycler.setAdapter(adapter);
            name.setText(receipt.getData().getUser());
            inn.setText(receipt.getData().getUserInn());
            //number.setText("Чек № " + document.getReceiptData().getNds18());
            shift.setText("Смена № " + receipt.getData().getShiftNumber());
            operator.setText("Кассир " + receipt.getData().getOperator());
        } else if (receipt.getFound() == false) {

        } else if (receipt.getFound() == null) {

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_cash) {
            startActivity(new Intent(this, CashListActivity.class));
            finish();
        } else if (id == R.id.nav_offer) {
            startActivity(new Intent(this, OfferListActivity.class));
            finish();
        } else if (id == R.id.nav_cards) {
            startActivity(new Intent(this, CardListActivity.class));
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

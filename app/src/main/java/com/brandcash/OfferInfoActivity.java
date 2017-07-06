package com.brandcash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandcash.model.Offer;
import com.brandcash.model.OfferData;
import com.brandcash.ui.PriceView;
import com.squareup.picasso.Picasso;

/**
 * Created by savva.volobuev on 28.05.2017.
 */

public class OfferInfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private OfferData offer;
    private TextView brandDescription;
    private TextView brandUrl;
    private TextView offerDescription;
    private PriceView priceView;
    private ImageView brandImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_offer_info);
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

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        offer = getIntent().getParcelableExtra(OfferListActivity.EXTRA_OFFER);

        brandDescription = (TextView) findViewById(R.id.brand_description);
        brandImage = (ImageView) findViewById(R.id.image);
        brandUrl = (TextView) findViewById(R.id.brand_url);
        offerDescription = (TextView) findViewById(R.id.description);
        priceView = (PriceView) findViewById(R.id.price);
        View exit = findViewById(R.id.footer);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfferInfoActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        brandDescription.setText(offer.getDescription());
        brandUrl.setText(offer.getBrand().getSiteUrl());
        offerDescription.setText(offer.getBrand().getDescription());
        priceView.setPrice(offer.getCashBack());
        Picasso.with(BrandcashApp.getAppContext())
                .load(offer.getBrand().getImageUrl())
                .into(brandImage);
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

package com.brandcash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.brandcash.model.Offer;
import com.brandcash.model.OfferData;
import com.brandcash.model.OffersResponse;
import com.brandcash.serverapi.ServerApiService;
import com.brandcash.serverapi.ServerClient;
import com.brandcash.ui.OfferListRecyclerAdapter;
import com.brandcash.util.SharedPrefs;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public class OfferListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OfferListRecyclerAdapter.OfferClickListener {
    public static final String EXTRA_OFFER = "EXTRA_OFFER";
    private NavigationView navigationView;
    private OffersResponse offers;
    private RecyclerView recyclerView;
    private OfferListRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.offer);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ((TextView) headerView.findViewById(R.id.nav_header_phone)).setText(SharedPrefs.getPrefPhone());
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(2).setChecked(true);
        setMenuCounter(R.id.nav_offer, 13);
        recyclerView = (RecyclerView) findViewById(R.id.offer_list);
        View exit = findViewById(R.id.footer);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfferListActivity.this, LoginActivity.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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

    private void setMenuCounter(@IdRes int itemId, int count) {
        TextView view = (TextView) navigationView.getMenu().findItem(itemId).getActionView().findViewById(R.id.counter);
        view.setText(count > 0 ? String.valueOf(count) : null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sync) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_cash) {
            startActivity(new Intent(this, CashListActivity.class));
            finish();
        } else if (id == R.id.nav_offer) {
           // startActivity(new Intent(this, OfferListActivity.class));
           // finish();
        } else if (id == R.id.nav_cards) {
            startActivity(new Intent(this, CardListActivity.class));
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        Call<OffersResponse> call = ServerClient.getServerApiService().listOffers(SharedPrefs.getPrefUserId(), SharedPrefs.getPrefSid());
        final MaterialDialog dialog = new MaterialDialog.Builder(OfferListActivity.this).content("Пожайлуста, подождите").progress(true, 0).build();
        dialog.show();
        call.enqueue(new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                dialog.dismiss();
                offers = response.body();
                if (offers != null) {
                    adapter = new OfferListRecyclerAdapter(offers.getItems(), OfferListActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(OfferListActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<OffersResponse> call, Throwable t) {
                dialog.dismiss();
                Log.d("httpserver", "fail");
            }
        });
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, OfferInfoActivity.class);
        intent.putExtra(EXTRA_OFFER, offers.getItems().get(position));
        startActivity(intent);
    }

}

package com.brandcash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.brandcash.model.CardData;
import com.brandcash.model.CardListResponse;
import com.brandcash.serverapi.ServerClient;
import com.brandcash.ui.CardListRecyclerAdapter;
import com.brandcash.util.SharedPrefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 11.07.2017.
 */

public class CardListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private List<CardData> items;
    private CardListRecyclerAdapter adapter;
    private Button addCard;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_cash) {

        } else if (id == R.id.nav_offer) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.card);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.card_list);
        addCard = (Button) findViewById(R.id.add_card);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardListActivity.this, CreateCardActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Call<CardListResponse> call = ServerClient.getServerApiService().listCards(SharedPrefs.getPrefUserId(), SharedPrefs.getPrefSid());
        call.enqueue(new Callback<CardListResponse>() {
            @Override
            public void onResponse(Call<CardListResponse> call, Response<CardListResponse> response) {
                CardListResponse resp = response.body();
                if (resp != null && resp.getItems() != null && !resp.getItems().isEmpty()) {
                    items = response.body().getItems();

                    adapter = new CardListRecyclerAdapter(items, CardListActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(CardListActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<CardListResponse> call, Throwable t) {
                Log.d("httpserver", "fail");
            }
        });
    }
}

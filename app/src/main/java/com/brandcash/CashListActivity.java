package com.brandcash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.brandcash.model.ReceiptData;
import com.brandcash.model.ReceiptListResponseData;
import com.brandcash.serverapi.ServerClient;
import com.brandcash.ui.BonusFragment;
import com.brandcash.ui.RubleFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 28.05.2017.
 */

public class CashListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private TabLayout tabs;
    private ViewPager viewPager;
    private CashListAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.cash);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabs = (TabLayout) findViewById(R.id.tabs);
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Call<ReceiptListResponseData> call = ServerClient.getServerApiService().list("TVRRMk9ERTROak13TVRrNAo=");
        call.enqueue(new Callback<ReceiptListResponseData>() {

            @Override
            public void onResponse(Call<ReceiptListResponseData> call, Response<ReceiptListResponseData> response) {
                if (response != null && response.body() != null) {
                    progressBar.setVisibility(View.GONE);
                    tabs.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                    tabs.setupWithViewPager(viewPager);
                    adapter = new CashListAdapter(getSupportFragmentManager(), response.body());
                    viewPager.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ReceiptListResponseData> call, Throwable t) {

            }
        });

    }

    public static class CashListAdapter extends FragmentStatePagerAdapter {
        private static int NUM_ITEMS = 2;
        private int mCurrentPosition = -1;
        private ReceiptListResponseData bonusData;

        public CashListAdapter(FragmentManager fragmentManager, ReceiptListResponseData bonusData) {
            super(fragmentManager);
            this.bonusData = bonusData;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RubleFragment();
                case 1:
                    return BonusFragment.newInstance(bonusData);
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Рубли";
            } else if (position == 1) {
                return "Баллы";
            }
            return "";
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

    }

}

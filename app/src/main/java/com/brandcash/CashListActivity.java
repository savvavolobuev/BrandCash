package com.brandcash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.brandcash.model.CashListResponseData;
import com.brandcash.model.ReceiptListResponseData;
import com.brandcash.serverapi.ServerClient;
import com.brandcash.ui.BonusFragment;
import com.brandcash.ui.RubleFragment;
import com.brandcash.util.SharedPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by savva.volobuev on 28.05.2017.
 */

public class CashListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String EXTRA_BONUSES_OPEN = "EXTRA_BONUSES_OPEN";
    private NavigationView navigationView;
    private TabLayout tabs;
    private ViewPager viewPager;
    private CashListAdapter adapter;
    private ProgressBar progressBar;
    private boolean bonusesOpen = false;

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
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ((TextView) headerView.findViewById(R.id.nav_header_phone)).setText(SharedPrefs.getPrefPhone());
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabs = (TabLayout) findViewById(R.id.tabs);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        View exit = findViewById(R.id.footer);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CashListActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        bonusesOpen = getIntent().getBooleanExtra(EXTRA_BONUSES_OPEN, false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
        } else if (id == R.id.nav_cash) {
           // startActivity(new Intent(this, CashListActivity.class));
          //  finish();
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

    @Override
    protected void onStart() {
        super.onStart();
        initCash();
    }

    private void initCash() {
        Call<CashListResponseData> call = ServerClient.getServerApiService().listCash(SharedPrefs.getPrefUserId(), SharedPrefs.getPrefSid());
        final MaterialDialog dialog = new MaterialDialog.Builder(CashListActivity.this).content("Пожайлуста, подождите").progress(true, 0).build();
        dialog.show();
        call.enqueue(new Callback<CashListResponseData>() {

            @Override
            public void onResponse(Call<CashListResponseData> call, Response<CashListResponseData> response) {
                dialog.dismiss();
                if (response.code() == 200) {
                    final CashListResponseData data = response.body();
                    if (data != null) {
                        if (response != null && response.body() != null) {
                            Call<ReceiptListResponseData> callReceipts = ServerClient.getServerApiService().listReceipts("TVRRMk9ERTROak13TVRrNAo=");
                            callReceipts.enqueue(new Callback<ReceiptListResponseData>() {

                                @Override
                                public void onResponse(Call<ReceiptListResponseData> call, Response<ReceiptListResponseData> response) {
                                    if (response != null && response.body() != null) {
                                        progressBar.setVisibility(View.GONE);
                                        tabs.setVisibility(View.VISIBLE);
                                        viewPager.setVisibility(View.VISIBLE);
                                        tabs.setupWithViewPager(viewPager);
                                        adapter = new CashListAdapter(getSupportFragmentManager(), response.body(), data);
                                        viewPager.setAdapter(adapter);
                                        if (bonusesOpen) {
                                            viewPager.setCurrentItem(1);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ReceiptListResponseData> call, Throwable t) {

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CashListResponseData> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }


    public static class CashListAdapter extends FragmentStatePagerAdapter {
        private static int NUM_ITEMS = 2;
        private int mCurrentPosition = -1;
        private ReceiptListResponseData bonusData;
        private CashListResponseData cashData;

        public CashListAdapter(FragmentManager fragmentManager, ReceiptListResponseData bonusData, CashListResponseData cashData) {
            super(fragmentManager);
            this.bonusData = bonusData;
            this.cashData = cashData;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return RubleFragment.newInstance(cashData);
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

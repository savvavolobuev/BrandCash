package com.brandcash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.brandcash.model.AccountData;
import com.brandcash.model.AccountMain;
import com.brandcash.model.Offer;
import com.brandcash.serverapi.ServerClient;
import com.brandcash.ui.FormatStringUtil;
import com.brandcash.ui.OfferListRecyclerAdapter;
import com.brandcash.ui.PriceView;
import com.brandcash.util.SharedPrefs;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private View qrCircle;
    private PriceView currentCash;
    private RelativeLayout cashList;
    private TextView offerCount;
    private TextView receiptSum;
    private TextView receiptCount;
    private RelativeLayout firstLaunchLayout;
    private RelativeLayout bonusesLayout;
    private TextView cashUpdated;
    private TextView bonusWord;
    private TextView bonusCount;
    private RelativeLayout offersRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        qrCircle = findViewById(R.id.qr);
        qrCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CaptureQrActivity.class));
            }
        });

        currentCash = (PriceView) findViewById(R.id.current_cash);
        cashUpdated = (TextView) findViewById(R.id.cash_updated);

        currentCash.setPrice(100);

        cashList = (RelativeLayout) findViewById(R.id.cash_list);
        cashList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CashListActivity.class));
            }
        });
        offersRelative = (RelativeLayout) findViewById(R.id.offers_relative);
        offersRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OfferListActivity.class));
            }
        });
        receiptSum = (TextView) findViewById(R.id.receipt_sum);
        receiptCount = (TextView) findViewById(R.id.receipt_count);
        offerCount = (TextView) findViewById(R.id.main_screen_offers_count);
        firstLaunchLayout = (RelativeLayout) findViewById(R.id.first_enter_relative);
        bonusesLayout = (RelativeLayout) findViewById(R.id.bonuses);
        bonusesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CashListActivity.class).putExtra(CashListActivity.EXTRA_BONUSES_OPEN, true));
            }
        });
        bonusWord = (TextView) findViewById(R.id.bonus_word);
        bonusCount = (TextView) findViewById(R.id.bonus_count);

        View exit = findViewById(R.id.footer);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initSettings();
    }

    private void initSettings() {
        Call<AccountMain> call = ServerClient.getServerApiService().getAccountData(SharedPrefs.getPrefUserId(), SharedPrefs.getPrefSid());
        call.enqueue(new Callback<AccountMain>() {
            @Override
            public void onResponse(Call<AccountMain> call, Response<AccountMain> response) {
                if (response != null && response.code() == 200) {
                    AccountMain data = response.body();
                    firstLaunchLayout.setVisibility(SharedPrefs.isPrefFirst() ? View.VISIBLE : View.GONE);
                    bonusesLayout.setVisibility(SharedPrefs.isPrefFirst() ? View.GONE : View.VISIBLE);
                    SharedPrefs.setPrefFirst(false);
                    offerCount.setText(data.getOffersCount() + "");
                    setMenuCounter(R.id.nav_offer, data.getOffersCount());
                    receiptCount.setText(data.getPoints().getReceiptsCounts() + " " + FormatStringUtil.getDependentStringReceipt(Integer.valueOf(data.getPoints().getReceiptsCounts())));
                    receiptSum.setText(" " + data.getPoints().getReceiptsSum() + FormatStringUtil.getDependentStringRuble(data.getPoints().getReceiptsSum()));
                    currentCash.setPrice(data.getCash().getBalance());
                    bonusCount.setText(data.getPoints().getBalance() + "");
                    bonusWord.setText(FormatStringUtil.getDependentStringBonus(data.getPoints().getBalance()));
                    SimpleDateFormat serverFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.ssssss");
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
                    Date date = null;
                    try {
                        date = serverFormat.parse(data.getCash().getUpdatedSt());
                        cashUpdated.setText("Обновлено " + format.format(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AccountMain> call, Throwable t) {
                Log.d("httpserver", "fail");
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
            //super.onBackPressed();
            new MaterialDialog.Builder(MainActivity.this)
                    .title(R.string.exit)
                    .autoDismiss(false)
                    //.content(R.string.)
                    .positiveText(R.string.positive)
                    .negativeText(R.string.negative)
                    //.positiveColor(setColor())
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    // .negativeColor(setColor())
                    // .typeface(titleAndActions, contentAndListItems)
                    .build()
                    .show();

        }
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        int size = navigationView.getMenu().size();
        for (int i = 0; i < size; i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }

    private void setMenuCounter(@IdRes int itemId, int count) {
        TextView view = (TextView) navigationView.getMenu().findItem(itemId).getActionView().findViewById(R.id.counter);
        view.setText(String.valueOf(count));
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

        } else if (id == R.id.nav_offer) {
            startActivity(new Intent(this, OfferListActivity.class));
        } else if (id==R.id.nav_cards ){
            startActivity(new Intent(this, CardListActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.brandcash.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brandcash.R;
import com.brandcash.model.CashListResponseData;

/**
 * Created by savva.volobuev on 25.06.2017.
 */

public class RubleFragment extends Fragment {
    private static final String ARG_CASH = "ARG_CASH";
    private PriceView total;
    private TextView updated;
    private CashListResponseData data;
    private RecyclerView recyclerView;
    private CashListRecyclerAdapter adapter;


    public static RubleFragment newInstance(CashListResponseData bonusData) {
        RubleFragment fragment = new RubleFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CASH, bonusData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getArguments().getParcelable(ARG_CASH);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ruble_fragment, container, false);
        total = (PriceView) view.findViewById(R.id.total_sum);
        updated = (TextView) view.findViewById(R.id.updated);
        recyclerView = (RecyclerView) view.findViewById(R.id.receipt_list);
        total.setPrice(data.getBalance());
        updated.setText("Обновлено " + data.getUpdatedAt());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new CashListRecyclerAdapter(data.getTransactions(), null);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}

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

import com.brandcash.OfferListActivity;
import com.brandcash.R;
import com.brandcash.model.ReceiptData;
import com.brandcash.model.ReceiptListResponseData;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by savva.volobuev on 25.06.2017.
 */

public class BonusFragment extends Fragment {
    private static final String ARG_BONUS = "ARG_BONUS";
    private TextView totalSum;
    private PriceView receiptSum;
    private TextView receiptCount;
    private TextView textBonus;
    private ReceiptListResponseData data;
    private RecyclerView recyclerView;
    private ReceiptListRecyclerAdapter adapter;


    public static BonusFragment newInstance(ReceiptListResponseData bonusData) {
        BonusFragment fragment = new BonusFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_BONUS, bonusData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getArguments().getParcelable(ARG_BONUS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bonus_fragment, container, false);
        receiptSum = (PriceView) view.findViewById(R.id.receipt_sum);
        receiptCount = (TextView) view.findViewById(R.id.receipt_count);
        totalSum = (TextView) view.findViewById(R.id.total_sum);
        textBonus = (TextView) view.findViewById(R.id.text_bonus);
        recyclerView = (RecyclerView) view.findViewById(R.id.receipt_list);

        totalSum.setText(820 + "");
        textBonus.setText(FormatStringUtil.getDependentStringBonus(820));

        receiptCount.setText(data.getTotalCount() + " " + FormatStringUtil.getDependentStringReceipt(data.getTotalCount()));
        receiptSum.setPrice(data.getTotalSum());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new ReceiptListRecyclerAdapter(data.getItems(), null);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}

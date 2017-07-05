package com.brandcash.ui;

import android.support.transition.Transition;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandcash.BrandcashApp;
import com.brandcash.R;
import com.brandcash.model.Transaction;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by savva.volobuev on 05.07.2017.
 */

public class CashListRecyclerAdapter extends RecyclerView.Adapter<CashListRecyclerAdapter.ViewHolder> {

    private List<Transaction> data;
    private OfferClickListener offerClickListener;
    private static final SimpleDateFormat serverFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.ssssss");
    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");


    public interface OfferClickListener {
        void onClick(int position);
    }

    public CashListRecyclerAdapter(List<Transaction> offers, OfferClickListener offerClickListener) {
        this.offerClickListener = offerClickListener;
        this.data = offers;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cash_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Transaction item = data.get(position);
        Date date = null;
        try {
            date = serverFormat.parse(item.getTime());
            holder.time.setText(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.type.setText(item.getOffer().getBrand().getCategory());
        holder.price.setPrice(Integer.valueOf(item.getSum()));
        holder.name.setText(item.getOffer().getBrand().getName());
        Picasso.with(BrandcashApp.getAppContext())
                .load(item.getOffer().getBrand().getImageUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView time;
        public TextView name;
        public TextView type;
        public PriceView price;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.type);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (PriceView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.image);
            type = (TextView) itemView.findViewById(R.id.type);

        }
    }
}


package com.brandcash.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandcash.BrandcashApp;
import com.brandcash.R;
import com.brandcash.model.Offer;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public class OfferListRecyclerAdapter extends RecyclerView.Adapter<OfferListRecyclerAdapter.ViewHolder> {

    private List<Offer> data;
    private OfferClickListener offerClickListener;


    public interface OfferClickListener {
        void onClick(int position);
    }

    public OfferListRecyclerAdapter(List<Offer> offers, OfferClickListener offerClickListener) {
        this.offerClickListener = offerClickListener;
        this.data = offers;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Offer item = data.get(position);
        holder.name.setText(item.getBrand().getBrandName());
        holder.description.setText(item.getBrand().getBrandDescription());
        holder.price.setPrice(item.getBudget());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offerClickListener.onClick(position);
            }
        });
        if (item.getActionShops() != null && !item.getActionShops().isEmpty()) {
            holder.address.setText(item.getActionShops().get(0).getAddress());
        }


        Picasso.with(BrandcashApp.getAppContext())
                .load(data.get(position).getBrand().getBrandImageUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView description;
        public PriceView price;
        public ImageView image;
        public TextView address;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            price = (PriceView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.image);
            address = (TextView) itemView.findViewById(R.id.address);
        }

    }
}

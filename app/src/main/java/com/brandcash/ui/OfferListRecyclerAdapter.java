package com.brandcash.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brandcash.R;
import com.brandcash.model.Offer;

import java.util.List;

/**
 * Created by savva.volobuev on 23.05.2017.
 */

public class OfferListRecyclerAdapter extends RecyclerView.Adapter<OfferListRecyclerAdapter.ViewHolder> {

    private List<Offer> data;

    public OfferListRecyclerAdapter(List<Offer> offers) {
        this.data = offers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getBrand().getBrandName());
        holder.description.setText(data.get(position).getBrand().getBrandDescription());
        holder.price.setText(data.get(position).getBudget());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView description;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            price = (TextView) itemView.findViewById(R.id.price);

        }
    }
}

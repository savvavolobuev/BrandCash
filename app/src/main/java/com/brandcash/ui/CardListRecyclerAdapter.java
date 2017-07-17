package com.brandcash.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandcash.CardListActivity;
import com.brandcash.R;
import com.brandcash.model.CardData;

import java.util.List;

/**
 * Created by savva.volobuev on 11.07.2017.
 */

public class CardListRecyclerAdapter extends RecyclerView.Adapter<CardListRecyclerAdapter.ViewHolder> {

    private List<CardData> items;

    public CardListRecyclerAdapter(List<CardData> items, Context context) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardData item = items.get(position);
        holder.cardNumber.setText(item.getNumber());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cardNumber;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            cardNumber = (TextView) itemView.findViewById(R.id.card_number);

        }
    }
}

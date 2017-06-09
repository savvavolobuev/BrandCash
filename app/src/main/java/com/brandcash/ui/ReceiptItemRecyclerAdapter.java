package com.brandcash.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brandcash.R;
import com.brandcash.model.ReceiptItem;

import java.util.List;

/**
 * Created by savva.volobuev on 09.06.2017.
 */

public class ReceiptItemRecyclerAdapter extends RecyclerView.Adapter<ReceiptItemRecyclerAdapter.ViewHolder> {

    private List<ReceiptItem> data;

    public ReceiptItemRecyclerAdapter(List<ReceiptItem> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReceiptItem item = data.get(position);
        holder.name.setText(item.getName());
        holder.bonuses.setText(item.getNds18() + "");
        holder.count.setText(item.getQuantity() + "x ");
        holder.price.setPrice(item.getPrice());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView count;
        public TextView bonuses;
        public PriceView price;


        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            count = (TextView) itemView.findViewById(R.id.count);
            bonuses = (TextView) itemView.findViewById(R.id.bonuses);
            price = (PriceView) itemView.findViewById(R.id.price);
        }

    }

}

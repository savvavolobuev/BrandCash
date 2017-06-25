package com.brandcash.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brandcash.BrandcashApp;
import com.brandcash.R;
import com.brandcash.model.ReceiptData;
import com.brandcash.model.ReceiptResponseData;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by savva.volobuev on 25.06.2017.
 */

public class ReceiptListRecyclerAdapter extends RecyclerView.Adapter<ReceiptListRecyclerAdapter.ViewHolder> {

    private List<ReceiptResponseData> data;
    private OfferClickListener offerClickListener;


    public interface OfferClickListener {
        void onClick(int position);
    }

    public ReceiptListRecyclerAdapter(List<ReceiptResponseData> offers, OfferClickListener offerClickListener) {
        this.offerClickListener = offerClickListener;
        this.data = offers;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_list_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ReceiptResponseData item = data.get(position);
        holder.receiptNumber.setText("Чек № " + item.getData().getRequestNumber());
        holder.status.setText(item.isFound() ? "Подтвержден": "В обработке");
        holder.price.setPrice(item.getData().getTotalSum());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView receiptNumber;
        public TextView status;
        public PriceView price;
        public ImageView image;
        public TextView plusReceipt;
        public TextView where;

        public ViewHolder(View itemView) {
            super(itemView);
            receiptNumber = (TextView) itemView.findViewById(R.id.receipt_number);
            status = (TextView) itemView.findViewById(R.id.status);
            price = (PriceView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.image);
            plusReceipt = (TextView) itemView.findViewById(R.id.plus_receipt);
            where = (TextView) itemView.findViewById(R.id.where);
        }
    }
}

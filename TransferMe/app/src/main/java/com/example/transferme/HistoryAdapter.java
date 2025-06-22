package com.example.transferme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<TransactionCategory> transactions;
    private Context context;

    public HistoryAdapter(List<TransactionCategory> transactions, Context context) {
        this.transactions = transactions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tranz, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionCategory transaction = transactions.get(position);

        holder.categoryText.setText(transaction.getName());
        holder.amountText.setText(transaction.getAmount());

        String url = "https://ubotxdvkhvusymbhrgvy.supabase.co/storage/v1/object/public/avatarscategory/";
        Glide.with(context)
                .load(url + transaction.getAvatar_category())
                .placeholder(R.drawable.button_off)
                .error(R.drawable.button_on)
                .into(holder.iconImage);
    }

    @Override
    public int getItemCount() { return transactions.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView categoryText;
        TextView amountText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconimage);
            categoryText = itemView.findViewById(R.id.nameText);
            amountText = itemView.findViewById(R.id.amountText);
        }
    }
}

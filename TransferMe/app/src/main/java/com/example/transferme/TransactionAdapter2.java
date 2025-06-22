package com.example.transferme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionAdapter2 extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<Transaction> transactions;

    public TransactionAdapter2(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView date;
        TextView amount;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.Name);
            date = itemView.findViewById(R.id.date);
            amount = itemView.findViewById(R.id.amount);
        }
    }

    @NonNull
    @Override
    public TransactionAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_outcoming, parent, false);
        return new TransactionAdapter.TransactionViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.TransactionViewHolder holder, int position) {
        Transaction currentItem = transactions.get(position);

        holder.name.setText(currentItem.getSenderName());
        holder.date.setText(currentItem.getDate());
        holder.amount.setText(currentItem.getAmount());
        holder.avatar.setId(currentItem.getAvatarResId());

    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}

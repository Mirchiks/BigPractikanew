package com.example.transferme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CashBackAdapter extends RecyclerView.Adapter<CashBackAdapter.CashBackViewHolder> {
    private List<CashBack> cashBacks;

    public CashBackAdapter(List<CashBack> cashBacks) {
        this.cashBacks = cashBacks;
    }

    @NonNull
    @Override
    public CashBackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cashback_item, parent, false);
        return new CashBackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CashBackViewHolder holder, int position) {
        CashBack cashBack = cashBacks.get(position);
        holder.categoryTextView.setText(cashBack.getCategory());
        holder.timeTextView.setText(cashBack.getTime());
        holder.amountTextView.setText(cashBack.getAmount());
    }

    @Override
    public int getItemCount() {
        return cashBacks.size();
    }

    static class CashBackViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTextView, timeTextView, amountTextView;

        public CashBackViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.category);
            timeTextView = itemView.findViewById(R.id.Time);
            amountTextView = itemView.findViewById(R.id.Amount);
        }
    }
}

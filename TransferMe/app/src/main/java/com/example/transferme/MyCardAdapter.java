package com.example.transferme;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.CardViewHolder> {
    private List<MyCard> cards;
    private OnCardClickListener listener;

    public interface OnCardClickListener {
        void onCardClick(MyCard card);
    }

    public MyCardAdapter(List<MyCard> cards, OnCardClickListener listener) {
        this.cards = cards;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        MyCard card = cards.get(position);

        holder.cardNumber.setText(card.getCardNumber());
        holder.cardHolder.setText(card.getCardHolder());
        holder.expiryDate.setText(card.getExpiryDate());
        holder.balance.setText(card.getBalance());
        holder.cardVendor.setText(card.getCardVendor());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCardClick(card);
            }
        });
    }


    @Override
    public int getItemCount() {
        return cards.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cardView;
        TextView cardNumber, cardHolder, expiryDate, balance, cardVendor;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.CardView);
            cardNumber = itemView.findViewById(R.id.CardNumber);
            cardHolder = itemView.findViewById(R.id.CardHolder);
            expiryDate = itemView.findViewById(R.id.ExpiryDate);
            balance = itemView.findViewById(R.id.BalanceAmount);
            cardVendor = itemView.findViewById(R.id.CardVendor);
        }
    }
}
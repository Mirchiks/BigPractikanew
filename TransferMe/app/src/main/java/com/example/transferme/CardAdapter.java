package com.example.transferme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private List<Card> cards;

    public CardAdapter(List<Card> cards) {
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cards.get(position);

        holder.cardName.setText(card.getName());
        holder.cardType.setText(card.getType());
        holder.cardNumber.setText(card.getNumber());
        holder.cardBalance.setText(card.getBalance());
        holder.cardImage.setImageResource(card.getImageRes());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        TextView cardName, cardType, cardNumber, cardBalance;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardimage);
            cardName = itemView.findViewById(R.id.cardname);
            cardType = itemView.findViewById(R.id.cardType);
            cardNumber = itemView.findViewById(R.id.cardnomer);
            cardBalance = itemView.findViewById(R.id.cardBalance);
        }
    }
}
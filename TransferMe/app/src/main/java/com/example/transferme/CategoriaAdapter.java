package com.example.transferme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.transferme.module.Category;
import com.google.android.material.chip.Chip;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {
    private Context context;
    private List<Category> categoryList;

    public CategoriaAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category, parent, false);
        return new CategoriaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdapter.ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        String url = "https://ubotxdvkhvusymbhrgvy.supabase.co/storage/v1/object/public/avatarscategory/";
        Glide.with(context)
                .load(url + category.getAvatar_category())
                .placeholder(R.drawable.button_off)
                .error(R.drawable.button_on)
                .into(holder.ivCategoryIcon);
        holder.tvCategoryName.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TransferNextActivity.class);
                if (context instanceof Activity) {
                    context.startActivity(intent);
                } else {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategoryIcon;
        TextView tvCategoryName;
        View line;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoryIcon = itemView.findViewById(R.id.ivCategoryIcon);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            line = itemView.findViewById(R.id.line);
        }
    }
}



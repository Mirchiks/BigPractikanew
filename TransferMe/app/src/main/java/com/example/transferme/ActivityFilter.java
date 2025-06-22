package com.example.transferme;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActivityFilter extends Dialog {
    public interface FilterListener {
        void onFilterApplied(Set<String> selectedSpecializations);
    }

    private final List<CategoryTranz> categoryTranzs;
    private final FilterListener listener;
    private final Set<String> selectedSpecializations = new HashSet<>();

    private ChipGroup specializationsChipGroup;
    private Button resetButton, applyButton;

    public ActivityFilter(@NonNull Context context, List<CategoryTranz> categorytranz , FilterListener listener) {
        super(context);
        this.categoryTranzs = categorytranz;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.history_item);

        Window window = getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.TOP);
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }

        specializationsChipGroup = findViewById(R.id.categoriesChipGroup);
        resetButton = findViewById(R.id.resetButton);
        applyButton = findViewById(R.id.applyButton);

        for (CategoryTranz specialization : categoryTranzs) {
            Chip chip = new Chip(getContext());
            chip.setText(specialization.getName());
            chip.setCheckable(true);
            chip.setChipBackgroundColorResource(R.color.white);
            chip.setChipStrokeColorResource(R.color.blue);
            chip.setTextColor(getContext().getResources().getColor(R.color.black));
            chip.setRippleColorResource(R.color.blue);

            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    selectedSpecializations.add(String.valueOf(specialization.getId()));
                    chip.setChipBackgroundColorResource(R.color.blue);
                    chip.setTextColor(getContext().getResources().getColor(R.color.white));
                } else {
                    selectedSpecializations.remove(specialization.getId());
                    chip.setChipBackgroundColorResource(R.color.white);
                    chip.setTextColor(getContext().getResources().getColor(R.color.black));
                }
            });
            specializationsChipGroup.addView(chip);
        }

        resetButton.setOnClickListener(v -> resetFilters());
        applyButton.setOnClickListener(v -> applyFilters());
    }

    private void resetFilters() {
        selectedSpecializations.clear();
        specializationsChipGroup.clearCheck();
    }

    private void applyFilters() {
        try {

            listener.onFilterApplied(selectedSpecializations);
            dismiss();
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Введите корректные значения цены", Toast.LENGTH_SHORT).show();
        }
    }
}

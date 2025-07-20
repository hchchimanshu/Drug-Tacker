package com.himanshuhc.drugtracker.presentation.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshuhc.drugtracker.R;
import com.himanshuhc.drugtracker.domain.model.Drug;

import java.util.ArrayList;
import java.util.function.Consumer;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.DrugViewHolder> {


    private List<Drug> drugList = new ArrayList<>();
    private final Consumer<Drug> onAddClick;
    private final Consumer<Drug> onItemClick;
//    private final OnDrugClickListener listener;

    public interface OnDrugClickListener {
        void onDrugClick(Drug drug);
    }

    public DrugAdapter(Consumer<Drug> onAddClick, Consumer<Drug> onItemClick) {
        this.onAddClick = onAddClick;
        this.onItemClick = onItemClick;
    }

    public void updateDrugs(List<Drug> drugs) {
        this.drugList = drugs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DrugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_medication, parent, false);
        return new DrugViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugViewHolder holder, int position) {
        Drug drug = drugList.get(position);
        holder.tvMedName.setText(drug.getPsn());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClick != null) {
                onItemClick.accept(drug);
            }
        });
        holder.imgArrow.setOnClickListener(v -> {
            if (onAddClick != null) {
                onAddClick.accept(drug);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drugList != null ? drugList.size() : 0;
    }

    static class DrugViewHolder extends RecyclerView.ViewHolder {
        TextView tvMedName;
        ImageView imgIcon, imgArrow;

        public DrugViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMedName = itemView.findViewById(R.id.tvMedName);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            imgArrow = itemView.findViewById(R.id.imgArrow);
        }
    }

}
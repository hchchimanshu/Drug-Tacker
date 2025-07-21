package com.himanshuhc.drugtracker.presentation.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.himanshuhc.drugtracker.R;
import com.himanshuhc.drugtracker.data.local.Medication;
import com.himanshuhc.drugtracker.domain.model.Drug;

import java.util.ArrayList;
import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.ViewHolder>{

    private List<Medication> medicationList = new ArrayList<>();

    public void setMedicationList(List<Medication> list) {
        this.medicationList = list;
        notifyDataSetChanged();
    }

    public Medication getItemAt(int position) {
        return medicationList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medication, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Medication med = medicationList.get(position);
        holder.name.setText(med.getName());

        holder.itemView.setOnClickListener(v -> {
            Medication medication = medicationList.get(holder.getAdapterPosition());

            // Convert Medication to Drug
            Drug drug = new Drug(
                    medication.getName(),
                    medication.getRxcui(),
                    medication.getPsn(),
                    false,
                    medication.getSynonym(),
                    medication.getTty(),
                    medication.getLanguage(),
                    medication.getSuppress()
            );

            // Pass all data to the detail fragment
            Bundle bundle = new Bundle();
            bundle.putString("drug_name", medication.getName());
            bundle.putString("rxcui", medication.getRxcui());
            bundle.putSerializable("drug", drug); // ✅ now drug won't be null

            Navigation.findNavController(v)
                    .navigate(R.id.action_myMedicationFragment_to_medicationDetailFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvMedName);
        }
    }
}

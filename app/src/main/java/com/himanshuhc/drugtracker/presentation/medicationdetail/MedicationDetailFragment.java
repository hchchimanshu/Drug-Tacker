package com.himanshuhc.drugtracker.presentation.medicationdetail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.himanshuhc.drugtracker.R;
import com.himanshuhc.drugtracker.domain.model.Drug;
import com.himanshuhc.drugtracker.domain.repository.MedicationRepository;

public class MedicationDetailFragment extends Fragment {

    private static final String ARG_DRUG_NAME = "drug_name";
    private static final String ARG_RXCUI = "rxcui";

    private MedicationDetailViewModel viewModel;
    private String drugName;
    private String rxcui;

    public MedicationDetailFragment() {
        // Required empty constructor
    }

    public static MedicationDetailFragment newInstance(String drugName, String rxcui) {
        MedicationDetailFragment fragment = new MedicationDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DRUG_NAME, drugName);
        args.putString(ARG_RXCUI, rxcui);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medication_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvDrugName = view.findViewById(R.id.medicineName);
        TextView btnAddMedication = view.findViewById(R.id.btn_add_medication);

        if (getArguments() != null) {
            drugName = getArguments().getString(ARG_DRUG_NAME);
            rxcui = getArguments().getString(ARG_RXCUI);
            tvDrugName.setText(drugName);
        }

        MedicationRepository repository = new MedicationRepository(requireContext());

        MedicationDetailViewModelFactory factory = new MedicationDetailViewModelFactory(repository);

        MedicationDetailViewModel viewModel = new ViewModelProvider(this, factory)
                .get(MedicationDetailViewModel.class);
//        viewModel = new ViewModelProvider(this).get(MedicationDetailViewModel.class);

        btnAddMedication.setOnClickListener(v -> {
            if (drugName != null && rxcui != null) {
                Drug drug = new Drug(rxcui, drugName, true);
                viewModel.addDrugToLocalList(drugName, rxcui, true);

                Toast.makeText(requireContext(), "Medication added", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(view).popBackStack(); // or navigate to MyMedication
            }
        });
    }
}
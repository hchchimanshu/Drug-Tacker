package com.himanshuhc.drugtracker.presentation.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.himanshuhc.drugtracker.R;
import com.himanshuhc.drugtracker.data.local.Medication;

public class MyMedicationFragment extends Fragment {

    private RecyclerView recyclerView;
    private MedicationAdapter adapter;
    private MyMedicationViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_medication, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Step 1: Initialize RecyclerView
        recyclerView = view.findViewById(R.id.rvMedications);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new MedicationAdapter();
        recyclerView.setAdapter(adapter);

        // Step 2: Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MyMedicationViewModel.class);

//        // For testing only — insert one API drug and one custom drug if not already present
//        if (!viewModel.medicationExists("12345")) {
//            viewModel.insertMedication("Paracetamol", "12345", false); // API drug
//        }
//        if (!viewModel.medicationExists("99999")) {
//            viewModel.insertMedication("My Custom Med", "99999", true); // Custom drug
//        }
        viewModel.loadMedications(); // Reload the list after insert

        // Step 3: Observe LiveData and update adapter
        viewModel.getMedicationsLiveData().observe(getViewLifecycleOwner(), medications -> {
            adapter.setMedicationList(medications);
        });

        // Step 4: Load medications from SQLite
        viewModel.loadMedications();

        // Step 5:  Set up Swipe-to-Delete
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Medication med = adapter.getItemAt(viewHolder.getAdapterPosition());
                viewModel.deleteMedication(med.getId());
            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        // Step 6: Handle button to go to SearchFragment (if needed)
        view.findViewById(R.id.btnSearchMedications).setOnClickListener(v -> {
            Toast.makeText(requireContext(), "To Search Screen", Toast.LENGTH_SHORT).show();
//            Navigation.findNavController(v).navigate(R.id.action_myMedicationFragment_to_searchFragment);
        });
    }
}
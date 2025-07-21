package com.himanshuhc.drugtracker.presentation.search;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import com.himanshuhc.drugtracker.R;
import com.himanshuhc.drugtracker.data.remote.api.RetrofitClient;
import com.himanshuhc.drugtracker.data.remote.repository.DrugRepositoryImpl;
import com.himanshuhc.drugtracker.domain.repository.DrugRepository;
import com.himanshuhc.drugtracker.domain.repository.MedicationRepository;
import com.himanshuhc.drugtracker.presentation.base.BaseFragment;

public class SearchFragment extends BaseFragment {

    private SearchView searchView;
    private TextView btnSearch;
    private RecyclerView recyclerView;
    private DrugAdapter adapter;
    private SearchViewModel viewModel;
    private MedicationRepository medicationRepository;
    private static final int MAX_MEDICATIONS = 7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set status bar color to match the fragment background
        setStatusBarColor(R.color.background_color);

        setProgressBar(view, R.id.progressBar);

        searchView = view.findViewById(R.id.searchView);
        btnSearch = view.findViewById(R.id.btn_search);
        recyclerView = view.findViewById(R.id.rv_search_results);
        TextView backBtn = view.findViewById(R.id.back_btn);

        // Ensure it's expanded and focused
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.clearFocus(); // if you don't want keyboard up immediately

        searchView.setQueryHint(getString(R.string.search_hint));

        medicationRepository = new MedicationRepository(requireContext());
        DrugRepository repository = new DrugRepositoryImpl(RetrofitClient.getApiService());
        SearchViewModelFactory factory = new SearchViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(SearchViewModel.class);

        adapter = new DrugAdapter(
                item -> {
                    int count = medicationRepository.getTotalMedicationCount();
                    if (count < MAX_MEDICATIONS) {
                        // Extract name and rxcui from Drug
                        String name = item.getPsn();
                        String rxcui = item.getRxcui();
                        boolean isCustom = false; // since it's coming from API

                        boolean added = medicationRepository.insertMedication(name, rxcui, isCustom, item.getName(), item.getSynonym(),
                                item.getTty(), item.getLanguage(), item.getSuppress());
                        if (added) {
                            Toast.makeText(requireContext(), "Added to My Medications", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "Already added", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(requireContext(), "You can only add up to 7 medications", Toast.LENGTH_SHORT).show();
                    }
                },
                item -> {
                    // TODO: You may pass drug ID or full object if needed for details
                    Bundle bundle = new Bundle();
                    bundle.putString("drug_name", item.getPsn());
                    bundle.putString("rxcui", item.getRxcui());
                    bundle.putSerializable("drug", item);
                    bundle.putBoolean("isFromSearch", true);
                    Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_medicationDetailFragment, bundle);
                }
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getSearchResults().observe(getViewLifecycleOwner(), results -> {
            showProgress(false);
            adapter.updateDrugs(results);
        });

        btnSearch.setOnClickListener(v -> {
            hideKeyboard();
            String query = searchView.getQuery().toString().trim();
            if (!query.isEmpty()) {
                showProgress(true);
                viewModel.searchDrugs(query);
            } else {
                Toast.makeText(requireContext(), "Enter a search term", Toast.LENGTH_SHORT).show();
            }
        });

        backBtn.setOnClickListener(v -> Navigation.findNavController(view).popBackStack());
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
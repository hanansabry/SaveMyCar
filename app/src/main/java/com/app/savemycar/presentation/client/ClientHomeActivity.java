package com.app.savemycar.presentation.client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.savemycar.Constants;
import com.app.savemycar.R;
import com.app.savemycar.viewmodels.RetrieveCarDataViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientHomeActivity extends AppCompatActivity {

    @BindView(R.id.modelsSpinner)
    Spinner modelsSpinner;
    @BindView(R.id.typesSpinner)
    Spinner typesSpinner;
    @BindView(R.id.categoriesSpinner)
    Spinner categoriesSpinner;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
        ButterKnife.bind(this);

        RetrieveCarDataViewModel retrieveCarDataViewModel = new ViewModelProvider(this).get(RetrieveCarDataViewModel.class);
        retrieveCarDataViewModel.retrieveAllModels();
        retrieveCarDataViewModel.retrieveAllTypes();
        retrieveCarDataViewModel.retrieveAllCategories();

        retrieveCarDataViewModel.getModelListMutableLiveData().observe(this, this::initiateModelsSpinner);
        retrieveCarDataViewModel.getTypeListMutableLiveData().observe(this, this::initiateTypesSpinner);
        retrieveCarDataViewModel.getCategoriesListMutableLiveData().observe(this, this::initiateCategoriesSpinner);
    }

    private void initiateTypesSpinner(List<String> types) {
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        typesAdapter.addAll(types);
        typesSpinner.setAdapter(typesAdapter);
    }

    private void initiateModelsSpinner(List<String> models) {
        ArrayAdapter<String> modelsAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        modelsAdapter.addAll(models);
        modelsSpinner.setAdapter(modelsAdapter);
    }

    private void initiateCategoriesSpinner(List<String> categories) {
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        categoriesAdapter.addAll(categories);
        categoriesSpinner.setAdapter(categoriesAdapter);
        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = categories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnNext)
    public void onNextClicked() {
        Intent intent = new Intent(this, CheckIssueActivity.class);
        intent.putExtra(Constants.CATEGORY_NAME, selectedCategory);
        startActivity(intent);
    }
}
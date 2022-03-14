package com.app.savemycar.presentation.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.savemycar.R;
import com.app.savemycar.model.Workshop;
import com.app.savemycar.viewmodels.AddWorkshopViewModel;
import com.app.savemycar.viewmodels.RetrieveCarDataViewModel;

import java.util.List;

public class CarWorkshopsActivity extends AppCompatActivity {

    @BindView(R.id.categoriesSpinner)
    Spinner categoriesSpinner;
    @BindView(R.id.nameTextEmail)
    EditText nameTextEmail;
    @BindView(R.id.regionTextEmail)
    EditText regionTextEmail;
    @BindView(R.id.addressTextEmail)
    EditText addressTextEmail;
    @BindView(R.id.phoneTextEmail)
    EditText phoneTextEmail;

    private String selectedCategory;
    private AddWorkshopViewModel addWorkshopViewModel;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_workshops);
        ButterKnife.bind(this);

        pd = new ProgressDialog(this);
        pd.setTitle("Loading");
        pd.show();

        RetrieveCarDataViewModel retrieveCarDataViewModel = new ViewModelProvider(this).get(RetrieveCarDataViewModel.class);
        retrieveCarDataViewModel.retrieveAllCategories();
        retrieveCarDataViewModel.getCategoriesListMutableLiveData().observe(this, this::initiateCategoriesSpinner);

        addWorkshopViewModel = new ViewModelProvider(this).get(AddWorkshopViewModel.class);
        addWorkshopViewModel.getSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Workshop is added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error while adding workshop, please try again", Toast.LENGTH_SHORT).show();
            }
        });
        addWorkshopViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
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
        pd.hide();
    }

    @OnClick(R.id.btnAddWorkshop)
    public void onAddWorkshopClicked() {
        String workshopName = nameTextEmail.getText().toString();
        String region = regionTextEmail.getText().toString();
        String address = addressTextEmail.getText().toString();
        String phone = phoneTextEmail.getText().toString();

        Workshop workshop = new Workshop();
        workshop.setName(workshopName);
        workshop.setCategory(selectedCategory);
        workshop.setPhone(phone);
        workshop.setAddress(address);
        workshop.setRegion(region);

        addWorkshopViewModel.addWorkshop(workshop);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}
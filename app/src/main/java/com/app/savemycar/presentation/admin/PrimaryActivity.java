package com.app.savemycar.presentation.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.app.savemycar.Constants;
import com.app.savemycar.CustomDialog;
import com.app.savemycar.R;
import com.app.savemycar.model.Secondary;
import com.app.savemycar.presentation.adapters.SecondariesAdapter;
import com.app.savemycar.viewmodels.AddSecondaryViewModel;
import com.app.savemycar.viewmodels.RetrieveSecondariesViewModel;

import java.util.List;

public class PrimaryActivity extends AppCompatActivity {

    @BindView(R.id.secondariesRecyclerView)
    RecyclerView secondariesRecyclerView;
    @BindView(R.id.editTextSecondaryName)
    EditText editTextSecondaryName;

    private AddSecondaryViewModel addSecondaryViewModel;
    private String primaryId;
    private SecondariesAdapter secondariesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);
        ButterKnife.bind(this);

        primaryId = getIntent().getStringExtra(Constants.PRIMARY_ID);

        addSecondaryViewModel = new ViewModelProvider(this).get(AddSecondaryViewModel.class);
        addSecondaryViewModel.getSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Secondary is added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error while adding secondary, Please try again", Toast.LENGTH_SHORT).show();
            }
        });

        RetrieveSecondariesViewModel retrieveSecondariesViewModel = new ViewModelProvider(this).get(RetrieveSecondariesViewModel.class);
        retrieveSecondariesViewModel.retrieveSecondaries(primaryId);
        retrieveSecondariesViewModel.getSecondaryListMutableLiveData().observe(this, this::initiateSecondariesRecyclerView);
    }

    private void initiateSecondariesRecyclerView(List<Secondary> secondaries) {
        secondariesAdapter = new SecondariesAdapter(secondaries, secondary -> {
            Intent intent = new Intent(PrimaryActivity.this, DiagnosisActivity.class);
            intent.putExtra(Constants.SECONDARY, secondary);
            startActivity(intent);
        });
        secondariesRecyclerView.setAdapter(secondariesAdapter);
        secondariesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        secondariesAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnAddSecondary)
    public void onAddSecondaryClicked() {
        String secondaryName = editTextSecondaryName.getText().toString();
        if (secondaryName.isEmpty()) {
            Toast.makeText(this, "You must add value", Toast.LENGTH_SHORT).show();
        } else {
            Secondary secondary = new Secondary();
            secondary.setName(secondaryName);
            addSecondaryViewModel.addNewSecondary(primaryId, secondary);
        }
    }
}
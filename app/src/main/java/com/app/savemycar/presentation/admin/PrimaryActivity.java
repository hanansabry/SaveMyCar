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
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.savemycar.Constants;
import com.app.savemycar.CustomDialog;
import com.app.savemycar.R;
import com.app.savemycar.model.Secondary;
import com.app.savemycar.presentation.adapters.SecondariesAdapter;
import com.app.savemycar.viewmodels.AddSecondaryViewModel;
import com.app.savemycar.viewmodels.RetrievePrimaryViewModel;
import com.app.savemycar.viewmodels.RetrieveSecondariesViewModel;

import java.util.List;

public class PrimaryActivity extends AppCompatActivity {

    @BindView(R.id.issueNameTextView)
    TextView issueNameTextView;
    @BindView(R.id.primaryNameTextView)
    TextView primaryNameTextView;
    @BindView(R.id.secondariesRecyclerView)
    RecyclerView secondariesRecyclerView;
    @BindView(R.id.editTextSecondaryName)
    EditText editTextSecondaryName;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.secondariesLabelTV)
    TextView secondariesLabelTV;

    private AddSecondaryViewModel addSecondaryViewModel;
    private String primaryId;
    private String issueId;
    private SecondariesAdapter secondariesAdapter;
    private String issueName, primaryName, secondaryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);
        ButterKnife.bind(this);

        issueId = getIntent().getStringExtra(Constants.ISSUE_ID);
        issueName = getIntent().getStringExtra(Constants.ISSUE_NAME);
        primaryId = getIntent().getStringExtra(Constants.PRIMARY_ID);

        //retrieve issue and primary name
        RetrievePrimaryViewModel retrievePrimaryViewModel = new ViewModelProvider(this).get(RetrievePrimaryViewModel.class);
        retrievePrimaryViewModel.retrievePrimaryByIds(issueId, primaryId);
        retrievePrimaryViewModel.getPrimaryMutableLiveData().observe(this, primary -> {
            issueNameTextView.setText(issueName);
            primaryName = primary.getName();
            primaryNameTextView.setText(primaryName);
        });

        addSecondaryViewModel = new ViewModelProvider(this).get(AddSecondaryViewModel.class);
        addSecondaryViewModel.getSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Secondary is added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error while adding secondary, Please try again", Toast.LENGTH_SHORT).show();
            }
        });

        RetrieveSecondariesViewModel retrieveSecondariesViewModel = new ViewModelProvider(this).get(RetrieveSecondariesViewModel.class);
        retrieveSecondariesViewModel.retrieveSecondaries(issueId, primaryId);
        retrieveSecondariesViewModel.getSecondaryListMutableLiveData().observe(this, this::initiateSecondariesRecyclerView);
    }

    private void initiateSecondariesRecyclerView(List<Secondary> secondaries) {
        if (secondaries != null && secondaries.size() > 0 ) {
            secondariesLabelTV.setVisibility(View.VISIBLE);
            secondariesAdapter = new SecondariesAdapter(secondaries, secondary -> {
                secondaryName = secondary.getName();
                Intent intent = new Intent(PrimaryActivity.this, DiagnosisActivity.class);
                intent.putExtra(Constants.ISSUE_ID, issueId);
                intent.putExtra(Constants.PRIMARY_ID, primaryId);
                intent.putExtra(Constants.SECONDARY_ID, secondary.getId());
                intent.putExtra(Constants.ISSUE_NAME, issueName);
                intent.putExtra(Constants.PRIMARY_NAME, primaryName);
                intent.putExtra(Constants.SECONDARY_NAME, secondary.getName());
                startActivity(intent);
            });
            secondariesRecyclerView.setAdapter(secondariesAdapter);
            secondariesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            secondariesAdapter.notifyDataSetChanged();
        } else {
            secondariesLabelTV.setVisibility(View.GONE);
            Toast.makeText(this, "No current secondaries, Please add one", Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.GONE);
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
            addSecondaryViewModel.addNewSecondary(issueId, primaryId, secondary);
        }
    }
}
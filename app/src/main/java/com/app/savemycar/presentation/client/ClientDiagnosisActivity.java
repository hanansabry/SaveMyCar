package com.app.savemycar.presentation.client;

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
import com.app.savemycar.R;
import com.app.savemycar.domain.usecase.RetrieveDiagnosisUseCase;
import com.app.savemycar.model.Workshop;
import com.app.savemycar.presentation.adapters.WorkshopsAdapter;
import com.app.savemycar.viewmodels.RetrieveDiagnosisViewModel;
import com.app.savemycar.viewmodels.RetrieveWorkshopsViewModel;

import java.util.List;

public class ClientDiagnosisActivity extends AppCompatActivity {

    @BindView(R.id.diagnosisEditText)
    EditText editTextDiagnosisDetails;
    @BindView(R.id.issueNameTextView)
    TextView issueNameTextView;
    @BindView(R.id.primaryNameTextView)
    TextView primaryNameTextView;
    @BindView(R.id.secondaryNameTextView)
    TextView secondaryNameTextView;
    @BindView(R.id.workshopsView)
    View workshopsView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.workshopsRecyclerView)
    RecyclerView workshopsRecyclerView;
    @BindView(R.id.noWorkshopsTextView)
    TextView noWorkshopsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_diagnosis);
        ButterKnife.bind(this);

        String issueId = getIntent().getStringExtra(Constants.ISSUE_ID);
        String primaryId = getIntent().getStringExtra(Constants.PRIMARY_ID);
        String secondaryId = getIntent().getStringExtra(Constants.SECONDARY_ID);
        String issueName = getIntent().getStringExtra(Constants.ISSUE_NAME);
        String primaryName = getIntent().getStringExtra(Constants.PRIMARY_NAME);
        String secondaryName = getIntent().getStringExtra(Constants.SECONDARY_NAME);
        String selectedCategory = getIntent().getStringExtra(Constants.CATEGORY_NAME);

        issueNameTextView.setText(issueName);
        primaryNameTextView.setText(primaryName);
        secondaryNameTextView.setText(secondaryName);

        RetrieveDiagnosisViewModel retrieveDiagnosisViewModel = new ViewModelProvider(this).get(RetrieveDiagnosisViewModel.class);
        retrieveDiagnosisViewModel.retrieveDiagnosis(issueId, primaryId, secondaryId);
        retrieveDiagnosisViewModel.getDiagnosisMutableLiveData().observe(this, diagnosis -> {
            if (diagnosis == null || diagnosis.isEmpty()) {
                Toast.makeText(this, "No available diagnosis", Toast.LENGTH_SHORT).show();
                editTextDiagnosisDetails.setText("No available diagnosis");
            } else {
                editTextDiagnosisDetails.setText(diagnosis);
            }
        });

        //retrieve workshops
        RetrieveWorkshopsViewModel retrieveWorkshopsViewModel = new ViewModelProvider(this).get(RetrieveWorkshopsViewModel.class);
        retrieveWorkshopsViewModel.retrieveWorkshops(selectedCategory);
        retrieveWorkshopsViewModel.getWorkshopsMutableLiveData().observe(this, this::initiateWorkshopsRecyclerView);
    }

    private void initiateWorkshopsRecyclerView(List<Workshop> workshops) {
        if (workshops != null && workshops.size() > 0) {
            noWorkshopsTextView.setVisibility(View.GONE);
            workshopsView.setVisibility(View.VISIBLE);
            WorkshopsAdapter workshopsAdapter = new WorkshopsAdapter(workshops);
            workshopsRecyclerView.setAdapter(workshopsAdapter);
            workshopsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            noWorkshopsTextView.setVisibility(View.VISIBLE);
            workshopsView.setVisibility(View.INVISIBLE);
        }
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }
}
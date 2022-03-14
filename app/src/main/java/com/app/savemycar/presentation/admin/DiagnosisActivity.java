package com.app.savemycar.presentation.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.savemycar.Constants;
import com.app.savemycar.R;
import com.app.savemycar.viewmodels.AddDiagnosisViewModel;

public class DiagnosisActivity extends AppCompatActivity {

    private String issueId, primaryId, secondaryId, issueName, primaryName, secondaryName;
    private AddDiagnosisViewModel addDiagnosisViewModel;
    @BindView(R.id.editTextDiagnosisDetails)
    EditText editTextDiagnosisDetails;
    @BindView(R.id.issueNameTextView)
    TextView issueNameTextView;
    @BindView(R.id.primaryNameTextView)
    TextView primaryNameTextView;
    @BindView(R.id.secondaryNameTextView)
    TextView secondaryNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        ButterKnife.bind(this);

        issueId = getIntent().getStringExtra(Constants.ISSUE_ID);
        primaryId = getIntent().getStringExtra(Constants.PRIMARY_ID);
        secondaryId = getIntent().getStringExtra(Constants.SECONDARY_ID);
        issueName = getIntent().getStringExtra(Constants.ISSUE_NAME);
        primaryName = getIntent().getStringExtra(Constants.PRIMARY_NAME);
        secondaryName = getIntent().getStringExtra(Constants.SECONDARY_NAME);

        issueNameTextView.setText(issueName);
        primaryNameTextView.setText(primaryName);
        secondaryNameTextView.setText(secondaryName);

        addDiagnosisViewModel = new ViewModelProvider(this).get(AddDiagnosisViewModel.class);
        addDiagnosisViewModel.getSuccess().observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Diagnosis is added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error while added diagnosis, Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnAddDiagnosis)
    public void onAddDiagnosisClicked() {
        String diagnosis = editTextDiagnosisDetails.getText().toString();
        if (diagnosis.isEmpty()) {
            Toast.makeText(this, "Please add diagnosis details", Toast.LENGTH_SHORT).show();
        } else {
            addDiagnosisViewModel.addNewDiagnosis(issueId, primaryId, secondaryId, diagnosis);
        }
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

}
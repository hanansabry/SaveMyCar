package com.app.savemycar.presentation.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.savemycar.Constants;
import com.app.savemycar.R;
import com.app.savemycar.model.Issue;
import com.app.savemycar.model.Primary;
import com.app.savemycar.model.Secondary;
import com.app.savemycar.viewmodels.RetrieveIssuesViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckIssueActivity extends AppCompatActivity {

    @BindView(R.id.issuesSpinner)
    Spinner issuesSpinner;
    @BindView(R.id.primarySpinner)
    Spinner primarySpinner;
    @BindView(R.id.secondariesSpinner)
    Spinner secondariesSpinner;
    private String selectedIssueId, selectedPrimaryId, selectedSecondaryId;
    private String selectedIssueName, selectedPrimaryName, selectedSecondaryName;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_issue);
        ButterKnife.bind(this);

        selectedCategory = getIntent().getStringExtra(Constants.CATEGORY_NAME);
        RetrieveIssuesViewModel retrieveIssuesViewModel = new ViewModelProvider(this).get(RetrieveIssuesViewModel.class);
        retrieveIssuesViewModel.retrieveAllIssues();
        retrieveIssuesViewModel.getIssuesListMutableLiveData().observe(this, this::initiateIssuesSpinner);
    }

    private void initiateIssuesSpinner(List<Issue> issues) {
        selectedIssueId = null;
        ArrayAdapter<String> issuesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        List<String> issuesNamesList = new ArrayList<>();
        for (Issue issue : issues) {
            issuesNamesList.add(issue.getName());
            initiatePrimariesSpinner(issue.getPrimaries());
        }

        issuesAdapter.addAll(issuesNamesList);
        issuesSpinner.setAdapter(issuesAdapter);
        issuesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Issue issue = issues.get(position);
                selectedIssueId = issue.getId();
                selectedIssueName = issue.getName();
                initiatePrimariesSpinner(issue.getPrimaries());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initiatePrimariesSpinner(HashMap<String, Primary> primaries) {
        selectedPrimaryId = null;
        if (primaries != null && primaries.size() > 0) {
            ArrayAdapter<String> primariesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
            List<String> primariesNameList = new ArrayList<>();
            List<String> primariesKeyList = new ArrayList<>();
            for (String key : primaries.keySet()) {
                Primary p = primaries.get(key);
                primariesNameList.add(p.getName());
                primariesKeyList.add(key);
                initiateSecondariesSpinner(p.getSecondaries());
            }
            primariesAdapter.addAll(primariesNameList);
            primarySpinner.setAdapter(primariesAdapter);
            primarySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String pKey = primariesKeyList.get(position);
                    Primary primary = primaries.get(pKey);
                    selectedPrimaryName = primary.getName();
                    selectedPrimaryId = pKey;
                    initiateSecondariesSpinner(primary.getSecondaries());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            primarySpinner.setAdapter(null);
        }
    }

    private void initiateSecondariesSpinner(HashMap<String, Secondary> secondaries) {
        selectedSecondaryId = null;
        if (secondaries != null && secondaries.size() > 0) {
            ArrayAdapter<String> secondariesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
            List<String> secondariesNameList = new ArrayList<>();
            List<String> secondariesKeyList = new ArrayList<>();
            for (String key : secondaries.keySet()) {
                Secondary s = secondaries.get(key);
                secondariesNameList.add(s.getName());
                secondariesKeyList.add(key);
            }
            secondariesAdapter.addAll(secondariesNameList);
            secondariesSpinner.setAdapter(secondariesAdapter);
            secondariesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String sKey = secondariesKeyList.get(position);
                    Secondary secondary = secondaries.get(sKey);
                    selectedSecondaryName = secondary.getName();
                    selectedSecondaryId = sKey;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            secondariesSpinner.setAdapter(null);
        }
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.btnNext)
    public void onNextClicked() {
        if (selectedIssueId == null || selectedPrimaryId == null || selectedSecondaryId == null) {
            Toast.makeText(this, "You must select issue, primary and secondary to continue", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, ClientDiagnosisActivity.class);
            intent.putExtra(Constants.ISSUE_ID, selectedIssueId);
            intent.putExtra(Constants.PRIMARY_ID, selectedPrimaryId);
            intent.putExtra(Constants.SECONDARY_ID, selectedSecondaryId);
            intent.putExtra(Constants.ISSUE_NAME, selectedIssueName);
            intent.putExtra(Constants.PRIMARY_NAME, selectedPrimaryName);
            intent.putExtra(Constants.SECONDARY_NAME, selectedSecondaryName);
            intent.putExtra(Constants.CATEGORY_NAME, selectedCategory);
            startActivity(intent);
        }
    }
}
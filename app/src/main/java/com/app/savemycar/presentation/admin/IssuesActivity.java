package com.app.savemycar.presentation.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.savemycar.Constants;
import com.app.savemycar.CustomDialog;
import com.app.savemycar.R;
import com.app.savemycar.model.Issue;
import com.app.savemycar.model.Primary;
import com.app.savemycar.presentation.adapters.IssuesExpandingAdapter;
import com.app.savemycar.viewmodels.AddIssueViewModel;
import com.app.savemycar.viewmodels.AddPrimaryViewModel;
import com.app.savemycar.viewmodels.RetrieveIssuesViewModel;
import com.diegodobelo.expandingview.ExpandingList;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IssuesActivity extends AppCompatActivity {

    @BindView(R.id.expanding_list_secondaries)
    ExpandingList expandingListIssues;
    @BindView(R.id.editTextIssueName)
    EditText editTextIssueName;
    @BindView(R.id.issuesTitleLabelTextView)
    TextView issuesTitleLabelTextView;
    private AddIssueViewModel addIssueViewModel;
    private AddPrimaryViewModel addPrimaryViewModel;
    private IssuesExpandingAdapter issuesExpandingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        ButterKnife.bind(this);

        addIssueViewModel = new ViewModelProvider(this).get(AddIssueViewModel.class);
        addIssueViewModel.getSuccess().observe(this, issue -> {
            if (issue != null) {
                Toast.makeText(this, "Issue is added successfully", Toast.LENGTH_SHORT).show();
                issuesExpandingAdapter.createNewItem(issue);
            } else {
                Toast.makeText(this, "Error while adding issue, please try again", Toast.LENGTH_SHORT).show();
            }
        });

        addIssueViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });

        RetrieveIssuesViewModel retrieveIssuesViewModel = new ViewModelProvider(this).get(RetrieveIssuesViewModel.class);
        retrieveIssuesViewModel.retrieveAllIssues();
        retrieveIssuesViewModel.getIssuesListMutableLiveData().observe(this, this::setupExpandingList);

        addPrimaryViewModel = new ViewModelProvider(this).get(AddPrimaryViewModel.class);
        addPrimaryViewModel.getPrimaryMutableLiveData().observe(this, primary -> {
            if (primary != null) {
                issuesExpandingAdapter.createSubItem(primary);
            } else {
                Toast.makeText(this, "Error while adding primary, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupExpandingList(List<Issue> issues) {
        if (issues == null || issues.isEmpty()) {
            issuesTitleLabelTextView.setVisibility(View.GONE);
        } else {
            issuesTitleLabelTextView.setVisibility(View.VISIBLE);
            issuesExpandingAdapter = new IssuesExpandingAdapter(expandingListIssues);
            issuesExpandingAdapter.setOnPrimarySubItemClicked(primary -> {
                Toast.makeText(IssuesActivity.this, primary.getName() + " is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, PrimaryActivity.class);
                intent.putExtra(Constants.PRIMARY_ID, primary.getName());
                startActivity(intent);
            });
            issuesExpandingAdapter.setOnAddPrimarySubItemCallback(new OnAddPrimarySubItemListener());
            for (Issue issue : issues) {
                issuesExpandingAdapter.createNewItem(issue);
            }
        }
    }

    @OnClick(R.id.btnAddIssue)
    public void onAddIssueClicked() {
        String issueName = editTextIssueName.getText().toString();
        addIssueViewModel.addNewIssue(issueName);
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    class OnAddPrimarySubItemListener implements IssuesExpandingAdapter.OnAddPrimarySubItemCallback {

        @Override
        public void onAddSubItem(Issue issue) {
            CustomDialog customDialog = new CustomDialog(IssuesActivity.this, new CustomDialog.DialogListener() {
                @Override
                public void onAddClicked(String value) {
                    if (value.isEmpty()) {
                        Toast.makeText(IssuesActivity.this, "You must add primary name", Toast.LENGTH_SHORT).show();
                    } else {
                        Primary primary = new Primary();
                        primary.setName(value);
                        addPrimaryViewModel.addNewPrimary(issue.getId(), primary);
                    }
                }
            });
            customDialog.show();
        }
    }
}
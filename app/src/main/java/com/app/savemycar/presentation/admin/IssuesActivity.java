package com.app.savemycar.presentation.admin;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.savemycar.CustomDialog;
import com.app.savemycar.R;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

public class IssuesActivity extends AppCompatActivity {

    @BindView(R.id.expanding_list_secondaries)
    ExpandingList expandingListIssues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        ButterKnife.bind(this);

        addExpandingItem("Issue 1", 5);
        addExpandingItem("Issue 2", 5);
    }

    @OnClick(R.id.btnAddIssue)
    public void onAddIssueClicked() {
        Toast.makeText(this, "Issue is clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnBack)
    public void onBackClicked() {
        onBackPressed();
    }

    private void addExpandingItem(String itemName, int subItemsCount) {
        ExpandingItem item = expandingListIssues.createNewItem(R.layout.expanding_issue_layout);
        ((TextView) item.findViewById(R.id.title)).setText(itemName);
        item.setIndicatorColorRes(R.color.colorAccent);
        item.setIndicatorIconRes(R.drawable.car_issues);
        item.createSubItems(subItemsCount);

        item.findViewById(R.id.add_primary_btn).setOnClickListener(v -> {
            CustomDialog customDialog = new CustomDialog(this, null);
            customDialog.show();
        });

        //get a sub item View
        addSubItemToExpandingItem(item, "Primary 1", 0);
        addSubItemToExpandingItem(item, "Primary 2", 1);
        addSubItemToExpandingItem(item, "Primary 3", 2);

        item.setIndicatorColorRes(R.color.colorAccent);
        item.setIndicatorIconRes(R.drawable.car_issues);
    }

    private void addSubItemToExpandingItem(ExpandingItem expandingItem, String subItemName, int index) {
        View subItemZero = expandingItem.getSubItemView(index);
        ((TextView) subItemZero.findViewById(R.id.sub_title)).setText(subItemName);

        subItemZero.setOnClickListener(v -> {
            Toast.makeText(IssuesActivity.this, "Primary is clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, PrimaryActivity.class));
        });
    }
}
package com.app.savemycar.presentation.adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.savemycar.R;
import com.app.savemycar.model.Issue;
import com.app.savemycar.model.Primary;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;

import java.util.ArrayList;
import java.util.List;

public class IssuesExpandingAdapter {

    private ExpandingList expandingListIssues;
    private OnAddPrimarySubItemCallback onAddPrimarySubItemCallback;
    private OnPrimarySubItemClicked onPrimarySubItemClicked;
    private ExpandingIssueListItem expandingItem;
    private List<ExpandingIssueListItem> expandingIssueListItems;

    public IssuesExpandingAdapter(ExpandingList expandingListIssues) {
        this.expandingListIssues = expandingListIssues;
        expandingIssueListItems = new ArrayList<>();
    }

    public void createNewItem(Issue issue) {
        expandingItem = new ExpandingIssueListItem(issue);
        expandingItem.setIssueItemTitle(issue.getName());
        if (issue.getPrimaries() != null && issue.getPrimaries().size() > 0) {
            for (String primaryKey : issue.getPrimaries().keySet()) {
                Primary primary = issue.getPrimaries().get(primaryKey);
                primary.setId(primaryKey);
                expandingItem.createSubItem(primary);
            }
        }
        expandingIssueListItems.add(expandingItem);
        expandingItem.getAddSubItemImageButton().setOnClickListener(v -> onAddPrimarySubItemCallback.onAddSubItem(issue, expandingIssueListItems.indexOf(expandingItem)));
    }


    public ExpandingIssueListItem getItemByIssue(Issue issue) {
        for (ExpandingIssueListItem expandingIssueListItem : expandingIssueListItems) {
            if (expandingIssueListItem.getIssue().getId().equals(issue.getId())) {
                return expandingIssueListItem;
            }
        }
        return null;
    }

    public void setOnPrimarySubItemClicked(OnPrimarySubItemClicked onPrimarySubItemClicked) {
        this.onPrimarySubItemClicked = onPrimarySubItemClicked;
    }

    public void setOnAddPrimarySubItemCallback (OnAddPrimarySubItemCallback onAddPrimarySubItemCallback) {
        this.onAddPrimarySubItemCallback = onAddPrimarySubItemCallback;
    }


    public class ExpandingIssueListItem {
        private ExpandingItem expandingItem;
        private TextView issueItemTitleTextView;
        private TextView primarySubItemTitleTextView;
        private ImageButton addSubItemImageButton;
        private Issue issue;
        int subItemsCount;

        ExpandingIssueListItem(Issue issue) {
            this.issue = issue;
            expandingItem = expandingListIssues.createNewItem(R.layout.expanding_issue_layout);
            issueItemTitleTextView = expandingItem.findViewById(R.id.title);
            addSubItemImageButton = expandingItem.findViewById(R.id.add_primary_btn);
            expandingItem.setIndicatorColorRes(R.color.colorAccent);
            expandingItem.setIndicatorIconRes(R.drawable.car_issues);
        }

        public void createSubItem(Primary primary) {
            View subItemView = createPrimarySubItem();
            setPrimarySubItemTitleTextView(primary.getName());
            subItemView.setOnClickListener(v -> onPrimarySubItemClicked.setOnPrimarySubItemClicked(issue, primary));
        }

        private View createPrimarySubItem() {
            View subItemView = expandingItem.createSubItem();
            primarySubItemTitleTextView = subItemView.findViewById(R.id.sub_title);
            return subItemView;
        }

        private void setIssueItemTitle(String issueItemTitle) {
            issueItemTitleTextView.setText(issueItemTitle);
        }

        private void setPrimarySubItemTitleTextView(String primarySubItemTitle) {
            primarySubItemTitleTextView.setText(primarySubItemTitle);
        }

        public ImageButton getAddSubItemImageButton() {
            return addSubItemImageButton;
        }

        public Issue getIssue() {
            return issue;
        }
    }

    public interface OnPrimarySubItemClicked {
        void setOnPrimarySubItemClicked(Issue issue, Primary primary);
    }

    public interface OnAddPrimarySubItemCallback {
        void onAddSubItem(Issue issue, int index);
    }
}

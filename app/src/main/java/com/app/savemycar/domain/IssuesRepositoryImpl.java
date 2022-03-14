package com.app.savemycar.domain;

import com.app.savemycar.Constants;
import com.app.savemycar.data.IssuesRepository;
import com.app.savemycar.model.Issue;
import com.app.savemycar.model.Primary;
import com.app.savemycar.model.Secondary;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class IssuesRepositoryImpl implements IssuesRepository {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    public void addNewIssue(Issue issue, MutableLiveData<Issue> issueMutableLiveData) {
        DatabaseReference issuesReference = database.getReference(Constants.ISSUES_NODE);
        String issueId = issuesReference.push().getKey();
        issuesReference.child(issueId).setValue(issue).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                issue.setId(issueId);
                issueMutableLiveData.setValue(issue);
            } else {
                issueMutableLiveData.setValue(null);
            }
        });
    }

    @Override
    public void retrieveAllIssues(MutableLiveData<List<Issue>> issuesMutableLiveData) {
        DatabaseReference issuesReference = database.getReference(Constants.ISSUES_NODE);
        issuesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Issue> issues = new ArrayList<>();
                for (DataSnapshot issueSnapshot : snapshot.getChildren()) {
                    Issue issue = issueSnapshot.getValue(Issue.class);
                    issue.setId(issueSnapshot.getKey());
                    issues.add(issue);
                }
                issuesMutableLiveData.setValue(issues);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

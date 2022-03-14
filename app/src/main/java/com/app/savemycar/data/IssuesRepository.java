package com.app.savemycar.data;

import com.app.savemycar.model.Issue;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface IssuesRepository {

    void addNewIssue(Issue issue, MutableLiveData<Issue> issueMutableLiveData);

    void retrieveAllIssues(MutableLiveData<List<Issue>> issuesMutableLiveData);
}

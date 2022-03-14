package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.IssuesRepository;
import com.app.savemycar.model.Issue;

import androidx.lifecycle.MutableLiveData;

public class AddIssueUseCase {

    private final IssuesRepository issuesRepository;

    public AddIssueUseCase(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }

    public void execute(Issue issue, MutableLiveData<Issue> issueMutableLiveData) {
        issuesRepository.addNewIssue(issue, issueMutableLiveData);
    }
}

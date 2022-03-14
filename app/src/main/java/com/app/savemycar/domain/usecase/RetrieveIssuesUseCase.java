package com.app.savemycar.domain.usecase;

import com.app.savemycar.data.IssuesRepository;
import com.app.savemycar.model.Issue;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RetrieveIssuesUseCase {

    private final IssuesRepository issuesRepository;

    public RetrieveIssuesUseCase(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }

    public void execute(MutableLiveData<List<Issue>> issuesListMutableLiveData) {
        issuesRepository.retrieveAllIssues(issuesListMutableLiveData);
    }
}

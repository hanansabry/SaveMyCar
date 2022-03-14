package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.RetrieveIssuesUseCase;
import com.app.savemycar.model.Issue;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveIssuesViewModel extends ViewModel {

    private final RetrieveIssuesUseCase retrieveIssuesUseCase;
    private MutableLiveData<List<Issue>> issuesListMutableLiveData = new MutableLiveData<>();

    public RetrieveIssuesViewModel() {
        retrieveIssuesUseCase = Injection.getRetrieveIssuesUseCase();
    }

    public void retrieveAllIssues() {
        retrieveIssuesUseCase.execute(issuesListMutableLiveData);
    }

    public MutableLiveData<List<Issue>> getIssuesListMutableLiveData() {
        return issuesListMutableLiveData;
    }
}

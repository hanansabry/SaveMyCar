package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.AddIssueUseCase;
import com.app.savemycar.model.Issue;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddIssueViewModel extends ViewModel {

    private final AddIssueUseCase addIssueUseCase;
    private MutableLiveData<Issue> success = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public AddIssueViewModel() {
        addIssueUseCase = Injection.getAddIssueUseCase();
    }

    public void addNewIssue(String issueName) {
        if (issueName.isEmpty()) {
            error.setValue("You must enter issue name");
        } else {
            Issue issue = new Issue();
            issue.setName(issueName);
            addIssueUseCase.execute(issue, success);
        }
    }

    public MutableLiveData<Issue> getSuccess() {
        return success;
    }

    public MutableLiveData<String> getError() {
        return error;
    }
}

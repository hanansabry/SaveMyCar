package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.RetrievePrimaryUseCase;
import com.app.savemycar.model.Primary;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrievePrimaryViewModel extends ViewModel {

    private final RetrievePrimaryUseCase retrievePrimaryUseCase;
    private MutableLiveData<Primary> primaryMutableLiveData = new MutableLiveData<>();

    public RetrievePrimaryViewModel() {
        retrievePrimaryUseCase = Injection.getRetrievePrimaryUseCase();
    }

    public void retrievePrimaryByIds(String issueId, String primaryId) {
        retrievePrimaryUseCase.execute(issueId, primaryId, primaryMutableLiveData);
    }

    public MutableLiveData<Primary> getPrimaryMutableLiveData() {
        return primaryMutableLiveData;
    }
}

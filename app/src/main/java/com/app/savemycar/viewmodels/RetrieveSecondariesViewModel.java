package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.RetrieveSecondariesUseCase;
import com.app.savemycar.model.Secondary;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveSecondariesViewModel extends ViewModel {

    private final RetrieveSecondariesUseCase retrieveSecondariesUseCase;
    private MutableLiveData<List<Secondary>> secondaryListMutableLiveData = new MutableLiveData<>();

    public RetrieveSecondariesViewModel() {
        retrieveSecondariesUseCase = Injection.getRetrieveSecondariesUseCase();
    }

    public void retrieveSecondaries(String issueId, String primaryId) {
        retrieveSecondariesUseCase.execute(issueId, primaryId, secondaryListMutableLiveData);
    }

    public MutableLiveData<List<Secondary>> getSecondaryListMutableLiveData() {
        return secondaryListMutableLiveData;
    }
}

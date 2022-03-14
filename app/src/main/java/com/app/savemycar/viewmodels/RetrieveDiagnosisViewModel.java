package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.RetrieveDiagnosisUseCase;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RetrieveDiagnosisViewModel extends ViewModel {

    private final RetrieveDiagnosisUseCase retrieveDiagnosisUseCase;
    private MutableLiveData<String> diagnosisMutableLiveData = new MutableLiveData<>();

    public RetrieveDiagnosisViewModel() {
        retrieveDiagnosisUseCase = Injection.getRetrieveDiagnosisUseCase();
    }

    public void retrieveDiagnosis(String issueId, String primaryId, String secondaryId) {
        retrieveDiagnosisUseCase.execute(issueId, primaryId, secondaryId, diagnosisMutableLiveData);
    }

    public MutableLiveData<String> getDiagnosisMutableLiveData() {
        return diagnosisMutableLiveData;
    }
}

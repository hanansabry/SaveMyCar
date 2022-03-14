package com.app.savemycar.viewmodels;

import com.app.savemycar.Injection;
import com.app.savemycar.domain.usecase.AddDiagnosisUseCase;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddDiagnosisViewModel extends ViewModel {

    private final AddDiagnosisUseCase addDiagnosisUseCase;
    private MutableLiveData<Boolean> success = new MutableLiveData<>();

    public AddDiagnosisViewModel() {
        addDiagnosisUseCase = Injection.getAddDiagnosisUseCase();
    }

    public void addNewDiagnosis(String issueId, String primaryId, String secondaryId, String diagnosis) {
        addDiagnosisUseCase.execute(issueId, primaryId, secondaryId, diagnosis, success);
    }

    public MutableLiveData<Boolean> getSuccess() {
        return success;
    }
}
